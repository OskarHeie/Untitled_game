package no.uib.inf101.galaga.model.ship;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

public class EnemyShip extends Ship {
  
  private CellPosition goal;
  private boolean movingRight;
  
  EnemyShip(char symbol, boolean[][] shape, CellPosition pos, CellPosition goal, boolean movingRight) {
    super(symbol, shape, pos);
    this.goal = goal;
    this.movingRight = movingRight;
  }
  
  
  /**
  * Shifts the ship to middle left of given grid and sets a
  * goal position where the ship will head and sets movingRight to true
  * 
  * @param gd GridDimension where ship should be placed
  * @param goalPos CellPosition where the ship should head
  * @return Identical ship except goal is set, it is moving right and it is shifted
  */
  public EnemyShip shiftedToLeftMiddle(GridDimension gd, CellPosition goalPos) {
    CellPosition newPos = new CellPosition(gd.rows()/2-1, -4);
    return new EnemyShip(symbol(), shape(), newPos, goalPos, true);
  }
  
  /**
  * Shifts the ship to middle right of given grid and sets a
  * goal position where the ship will head and sets movingRight to false
  * 
  * @param gd GridDimension where ship should be placed
  * @param goalPos CellPosition where the ship should head
  * @return Identical ship except goal is set, it is not moving right and it is shifted
  */
  public EnemyShip shiftedToRightMiddle(GridDimension gd, CellPosition goalPos) {
    CellPosition newPos = new CellPosition(gd.rows()/2-2, gd.cols()-1);
    return new EnemyShip(symbol(), shape(), newPos, goalPos, false);
  }
  
  /**
  * Gets the position one in front of the middle of the ship
  * which is pointing downward
  * 
  * @return CellPosition in front of ship
  */
  public CellPosition getFrontPosition() {
    CellPosition newPos = new CellPosition(pos().row()+5, pos().col()+2);
    return newPos;
  }
  
  @Override 
  public EnemyShip shiftedBy(int deltaRow, int deltaCol) {
    CellPosition newPos = new CellPosition(pos().row()+deltaRow, pos().col()+deltaCol);
    return new EnemyShip(symbol(), shape(), newPos, goal, movingRight);
  }
  
  /**
  * Function to return if ship is at the row it is
  * supposed to head to
  * 
  * @return true if at correct row and false if not
  */
  public boolean isAtCorrectRow() {
    return (this.goal.row() == pos().row());
  }
  
  /**
  * Function to return if ship is at the col it is
  * supposed to head to
  * 
  * @return true if at correct col and false if not
  */
  public boolean isAtCorrectCol() {
    return (this.goal.col() == pos().col());
  }
  
  /**
  * boolean for if the ship is moving right or not
  * 
  * @return true if moving right, false if not
  */
  public boolean movingRight() {
    return this.movingRight;
  }
  
  static EnemyShip newEnemyShip() {
    boolean[][] shape = new boolean[][] {
      {false, false, true, false, false},
      {true, true, true, true, true}, 
      {false, true, true, true, false}, 
      {false, false, true, false, false}, 
      {false, false, true, false, false}
    };
    
    return new EnemyShip('E', shape, new CellPosition(0, 0), new CellPosition(0, 0), true);
  }
}
