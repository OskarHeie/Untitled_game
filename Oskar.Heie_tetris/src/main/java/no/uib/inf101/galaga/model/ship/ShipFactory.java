package no.uib.inf101.galaga.model.ship;

public interface ShipFactory {

  /**
   * Creates a player ship
   * 
   * @return a player ship
   */
  public PlayerShip getPlayerShip();

  /**
   * Creates a missile
   * 
   * @return a missile
   */
  public Missile getMissile();

  /**
   * Creates a enemy ship
   * 
   * @return a enemy ship
   */
  public EnemyShip getEnemyShip();

}
