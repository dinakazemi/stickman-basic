package stickman.model;
/** Creates the strategy for a moving enemy to move in the game */
public class MovingEnemyMovement implements EnemyMovement {

  /**
   * Moves the enemy by implementing a bouncy movement behaviour. The enemy moves either in the y or
   * x direction, depending on which direction it is told to move in
   *
   * @param enemy The enemy that is moving, Should be a moving enemy
   */
  @Override
  public void move(Enemy enemy) {

    double initialXPos = enemy.getInitialXPos();
    double initialYPos = enemy.getInitialYPos();

    if (enemy.IsMovingX() == 1) {
      if (initialXPos - enemy.getXPos() > -0.25) {
        enemy.setMovementDir(1);
      }
      if (initialXPos - enemy.getXPos() < -30.75) {
        enemy.setMovementDir(-1);
      }
      if (enemy.getMovementDir() == 1) {
        enemy.setXPos(enemy.getXPos() + 0.25);
      } else if (enemy.getMovementDir() == -1) {
        enemy.setXPos(enemy.getXPos() - 0.25);
      }

    } else if (enemy.IsMovingY() == 1) {
      if (initialYPos - enemy.getYPos() > -0.25) {
        enemy.setMovementDir(1);
      }
      if (initialYPos - enemy.getYPos() < -50.75) {
        enemy.setMovementDir(-1);
      }
      if (enemy.getMovementDir() == 1) {
        enemy.setYPos(enemy.getYPos() + 0.25);
      } else if (enemy.getMovementDir() == -1) {
        enemy.setYPos(enemy.getYPos() - 0.25);
      }
    }
  }
}
