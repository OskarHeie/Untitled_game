package no.uib.inf101.galaga.model.ship;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

public class PlayerShip extends Ship{
  
  PlayerShip(char symbol, boolean[][] shape, CellPosition pos) {
    super(symbol, shape, pos);
  }
  
  /**
  * Gets the position one in front of the middle of the ship
  * which is pointing upward
  * 
  * @return CellPosition in front of ship
  */
  public CellPosition getFrontPosition() {
    CellPosition newPos = new CellPosition(pos().row()-1, pos().col()+2);
    return newPos;
  }
  
  /**
  * Shifts the ship to the bottom centre of a given grid
  * 
  * @param gd dimension of the grid to place on
  * @return an identical ship except shited
  */
  public PlayerShip shiftedToBottomCenterOf(GridDimension gd) {
    CellPosition newPos = new CellPosition(gd.rows()-7, (gd.cols()/2)-2);
    return new PlayerShip(symbol(), shape(), newPos);
  }
  
  @Override 
  public PlayerShip shiftedBy(int deltaRow, int deltaCol) {
    CellPosition newPos = new CellPosition(pos().row()+deltaRow, pos().col()+deltaCol);
    return new PlayerShip(symbol(), shape(), newPos);
  }
  
  /**
  * Returns the middle position of the ship
  * 
  * @return Cellposition of the middle of the ship
  */
  public CellPosition getMiddlePosition() {
    return new CellPosition(pos().row()+2, pos().col()+2);
  }
  
  static PlayerShip newPlayerShip() {
    boolean[][] shape = new boolean[][] {
      {false, false, true, false, false},
      {false, true, true, true, false}, 
      {true, true, true, true, true}, 
      {false, true, true, true, false}, 
      {false, false, true, false, false}
    };
    
    return new PlayerShip('P', shape, new CellPosition(0, 0));
  }
  
}
