package no.uib.inf101.galaga.model;

import java.util.ArrayList;
import java.util.Random;
import no.uib.inf101.galaga.controller.ControllableGalagaModel;
import no.uib.inf101.galaga.model.ship.EnemyShip;
import no.uib.inf101.galaga.model.ship.Missile;
import no.uib.inf101.galaga.model.ship.PlayerShip;
import no.uib.inf101.galaga.model.ship.Ship;
import no.uib.inf101.galaga.model.ship.ShipFactory;
import no.uib.inf101.galaga.view.ViewableGalagaModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class GalagaModel implements ViewableGalagaModel, ControllableGalagaModel {

  private GalagaBoard board;
  private ShipFactory shipFactory;
  private GameState gameState;
  private ArrayList<EnemyShip> enemies;
  private ArrayList<Missile> missiles;
  private PlayerShip ship;
  private static int ticks;
  private static int lives;
  private static int score;
  private static boolean spawn;

  public GalagaModel(GalagaBoard board, ShipFactory shipFactory) {
    this.board = board;
    this.shipFactory = shipFactory;
    this.ship = shipFactory.getPlayerShip().shiftedToBottomCenterOf(board);
    this.enemies = new ArrayList<>();
    this.missiles = new ArrayList<>();
    GalagaModel.ticks = 10;
    GalagaModel.lives = 3;
    GalagaModel.score = 0;
    GalagaModel.spawn = true;

    this.gameState = GameState.GAME_START;
  }

  @Override
  public boolean moveShip(int deltaCol) {
    PlayerShip candidate = this.ship.shiftedBy(0, deltaCol);
    if (legalMove(candidate)) {
      this.ship = candidate;
      return true;
    }
    return false;
  }

  @Override
  public void clockTick() {
    spawnEnemy();
    moveMissile();
    moveEnemy();
    enemyShoot();
    ticks += 1;
    if (ticks == 26) {
      ticks = 0;
    }
  }

  @Override
  public boolean shoot() {
    Missile candidate = shipFactory.getMissile().placeMissilePlayer(ship.getFrontPosition());
    if (legalMove(candidate)) {
      missiles.add(candidate);
      return true;
    }
    return false;
  }

  @Override
  public GridDimension getDimension() {
    return board;
  }

  @Override
  public Iterable<GridCell<Character>> getTilesOnBoard() {
    return board;
  }

  @Override
  public Iterable<GridCell<Character>> getPlayerShip() {
    return ship;
  }

  @Override
  public ArrayList<Missile> getMissiles() {
    return missiles;
  }

  @Override
  public ArrayList<EnemyShip> getEnemies() {
    return enemies;
  }

  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public int dropRate() {
    return 75;
  }

  @Override
  public int getLives() {
    return lives;
  }

  @Override
  public int getScore() {
    return score;
  }

  @Override
  public void startGame() {
    this.gameState = GameState.ACTIVE_GAME;
  }

  // Helper method to check if attempted move i legal
  private boolean legalMove(Ship piece) {
    for (GridCell<Character> cell : piece) {
      if (!board.positionIsOnGrid(cell.pos()))
        return false;
    }
    return true;
  }

  // Helper method for clock tick to spawn enemies
  private void spawnEnemy() {
    if (spawn) {
      if (ticks == 25) {
        Random random = new Random();
        int randNmbr = random.nextInt(2);
        if (randNmbr == 0) {
          EnemyShip candidate = shipFactory.getEnemyShip().shiftedToLeftMiddle(board, calculateGoalPos());
          enemies.add(candidate);
        } else {
          EnemyShip candidate = shipFactory.getEnemyShip().shiftedToRightMiddle(board, calculateGoalPos());
          enemies.add(candidate);
        }
      }
    } else {
      if (enemies.size() == 0) {
        spawn = true;
      }
    }
  }

  // Helper method to check if a missile has hit a ship
  private void missileHit() {
    for (int i = 0; i < missiles.size(); i++) {
      if (missiles.get(i).getFromPlayer()) {
        for (GridCell<Character> cell1 : missiles.get(i)) {
          if (enemyHit(cell1)) {
            missiles.remove(i);
            break;
          }
        }
      } else {
        if (missiles.get(i).getPos().row() < 50) {
          continue;
        }
        for (GridCell<Character> cell1 : missiles.get(i)) {
          if (playerHit(cell1)) {
            missiles.remove(i);
            break;
          }

        }
      }
    }
  }

  

  // Helper method to check if an enemy has been hit by one
  // off the cells in a missile
  private boolean enemyHit(GridCell<Character> cell1) {
    for (int j = 0; j < enemies.size(); j++) {
      for (GridCell<Character> cell2 : enemies.get(j)) {
        if (cell1.pos().row() == cell2.pos().row() && cell1.pos().col() == cell2.pos().col()) {
          enemies.remove(j);
          score += 100;
          return true;
        }
      }
    }
    return false;
  }

  // Helper method to check if a player has been hit by one
  // off the cells in a missile
  private boolean playerHit(GridCell<Character> cell1) {
    for (GridCell<Character> cell3 : this.ship) {
      if (cell1.pos().row() == cell3.pos().row() && cell1.pos().col() == cell3.pos().col()) {
        if (lives == 1) {
          lives = 0;
          this.gameState = GameState.GAME_OVER;
          return true;
        } else {
          lives -= 1;
          return true;
        }
      }
    }
    return false;
  }

  // Helper method to calculate position an enemy ship
  // should end up in
  private int counter = 0;

  private CellPosition calculateGoalPos() {
    if (counter < 7) {
      counter += 1;
      return new CellPosition(12, 3 + 7 * counter);
    } else if (counter < 14) {
      counter += 1;
      if (counter == 14) {
        counter = 0;
        spawn = false;
      }
      return new CellPosition(19, 3 + 7 * Math.abs(counter - 7));
    } else {
      return new CellPosition(0, 0);
    }
  }

  // Helper method to move enemies
  private void moveEnemy() {
    if (ticks % 2 == 1) {
      return;
    }
    for (int i = 0; i < enemies.size(); i++) {
      if (enemies.get(i).isAtCorrectCol() && enemies.get(i).isAtCorrectRow()) {
        continue;
      }
      int deltaRow = 0;
      int deltaCol = 0;
      if (enemies.get(i).isAtCorrectRow()) {
        if (enemies.get(i).movingRight()) {
          deltaCol = 1;
        } else {
          deltaCol = -1;
        }
      } else if (enemies.get(i).isAtCorrectCol()) {
        deltaRow = -1;
      } else {
        if (ticks % 4 == 0) {
          deltaRow = -1;
        } else if (enemies.get(i).movingRight()) {
          deltaCol = 1;
        } else {
          deltaCol = -1;
        }
      }
      EnemyShip candidate = enemies.get(i).shiftedBy(deltaRow, deltaCol);
      enemies.set(i, candidate);
    }
  }

  // Helper method for clock tick to move missiles
  private void moveMissile() {
    for (int i = 0; i < missiles.size(); i++) {
      if (missiles.get(i).getFromPlayer()) {
        Missile candidate = missiles.get(i).shiftedBy(-2, 0);
        if (legalMove(candidate)) {
          missiles.set(i, candidate);
        } else {
          missiles.remove(i);
        }
      } else {
        Missile candidate = enemyMissileMovement(missiles.get(i));
        if (legalMove(candidate)) {
          missiles.set(i, candidate);
        } else {
          missiles.remove(i);
        }
      }
    }
    missileHit();
  }

  // Helper method to make an enemy shoot
  int n = 100;
  public Object getEnemies;

  private void enemyShoot() {
    Random random1 = new Random();
    if (random1.nextInt(n + 1) == n) {
      n = 30;
      if (enemies.size() > 0) {
        Random random2 = new Random();
        int randomInt = random2.nextInt(enemies.size());
        Missile candidate = shipFactory.getMissile().placeMissileEnemy(enemies.get(randomInt).getFrontPosition(),
            this.ship.getMiddlePosition());

        if (legalMove(candidate)) {
          missiles.add(candidate);
        }
      }
    } else {
      n -= 1;
    }

  }

  // Helper method to move an enemy missile
  private Missile enemyMissileMovement(Missile missile) {
    int deltaRow = missile.getGoal().row() - missile.getPos().row();
    int deltaCol = missile.getGoal().col() - missile.getPos().col();

    Missile candidate = missile;
    if (Math.abs(deltaCol) < 2) {
      candidate = missile.shiftedBy(2, 0);
    } else if (deltaCol > 0 && deltaCol * 2 < deltaRow) {
      candidate = missile.shiftedBy(2, 1);
    } else if (deltaCol < 0 && deltaCol * 2 < deltaRow) {
      candidate = missile.shiftedBy(2, -1);
    } else if (deltaCol > 0) {
      candidate = missile.shiftedBy(1, 1);
    } else {
      candidate = missile.shiftedBy(1, -1);
    }
    return candidate;
  }
}
