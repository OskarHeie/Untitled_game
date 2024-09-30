package no.uib.inf101.galaga.model.ship;

public class VariableShipFactory implements ShipFactory {

  @Override
  public PlayerShip getPlayerShip() {
    return PlayerShip.newPlayerShip();
  }

  @Override
  public Missile getMissile() {
    return Missile.newMissile();
  }

  @Override
  public EnemyShip getEnemyShip() {
    return EnemyShip.newEnemyShip();
  }

}
