package no.uib.inf101.galaga.view;

import java.util.ArrayList;

import no.uib.inf101.galaga.model.GameState;
import no.uib.inf101.galaga.model.ship.EnemyShip;
import no.uib.inf101.galaga.model.ship.Missile;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public interface ViewableGalagaModel {

  /**
   * The dimensions of the board, i.e. number of rows and columns
   *
   * @return an object of type GridDimension
   */
  GridDimension getDimension();

  /**
   * Iterates over the tiles on the board
   *
   * @return an iterable object
   */
  Iterable<GridCell<Character>> getTilesOnBoard();

  /**
   * An object that when iterated over returns all positions and corresponding
   * vales
   *
   * @return an iterable object of type GridCell<Character>
   */
  Iterable<GridCell<Character>> getPlayerShip();

  /**
   * Gives a list of all enemies which are EnemyShips
   * 
   * @return an iterable object of enemies
   */
  ArrayList<EnemyShip> getEnemies();

  /**
   * Gives a list of all missiles which are Missiles
   * 
   * @return an iterable object of missiles
   */
  ArrayList<Missile> getMissiles();

  /**
   * Tells us the state of the game
   *
   * @return the state of the game
   */
  GameState getGameState();

  /**
   * Tells us the score of the game
   *
   * @return the score of the game
   */
  int getScore();

  /**
   * Tells us the amount of lives a player has left
   *
   * @return amount of lives left for player
   */
  int getLives();

}
