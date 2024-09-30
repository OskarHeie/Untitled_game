package no.uib.inf101.galaga.model.ship;

import no.uib.inf101.grid.CellPosition;

public class Missile extends Ship {
  
  private CellPosition goal;
  private boolean fromPlayer;
  
  Missile(char symbol, boolean[][] shape, CellPosition pos, boolean fromPlayer, CellPosition goal) {
    super(symbol, shape, pos);
    this.fromPlayer = fromPlayer;
    this.goal = goal;
  }
  
  @Override 
  public Missile shiftedBy(int deltaRow, int deltaCol) {
    CellPosition newPos = new CellPosition(pos().row()+deltaRow, pos().col()+deltaCol);
    return new Missile(symbol(), shape(), newPos, this.fromPlayer, this.goal);
  }
  
  /**
  * Intended for use for player ship, puts the missile at given
  * position which should be at the front of the ship.
  * Creates an identical missile at input position where
  * fromPlayer is true
  * 
  * @param pos CellPosition where you want missile to be placed
  * @return Identical missile except it is shifted and from the player
  */
  public Missile placeMissilePlayer(CellPosition pos) {
    CellPosition newPos = new CellPosition(pos.row()-2, pos.col()-1);
    return new Missile(symbol(), shape(), newPos, true, null);
  }
  
  /**
  * Intended for use for enemy ship, puts the missile at given
  * position which should be at the front of the ship.
  * Creates an identical missile at input position where
  * fromPlayer is false
  * 
  * @param pos CellPosition where you want missile to be placed
  * @return Identical missile except it is shifted and not from the player
  */
  public Missile placeMissileEnemy(CellPosition pos, CellPosition goal) {
    CellPosition newPos = new CellPosition(pos.row()-1, pos.col()-1);
    return new Missile(symbol(), shape(), newPos, false, goal);
  }
  
  static Missile newMissile() {
    
    boolean[][] shape = new boolean[][] {
      { false, false, false },
      {  false,  true,  false },
      { false,  true, false }
    };
    
    return new Missile('M', shape, new CellPosition(0, 0), false, null);
  }
  
  /**
  * Returns true if missile was fired from player or
  * false if missile was fired from enemy
  * 
  * @return bool that indicates if it was fired from player or not
  */
  public boolean getFromPlayer() {
    return this.fromPlayer;
  }
  
  /**
  * Simple getter for missile position
  * 
  * @return CellPosition of missile
  */
  public CellPosition getPos() {
    return this.pos();
  }
  
  /**
  * Simple getter for missile goal position, null if
  * it was fired from player, player position if fired from
  * enemy
  * 
  * @return CellPosition of missiles goal
  */
  public CellPosition getGoal() {
    return this.goal;
  }
}
