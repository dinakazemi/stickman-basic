package stickman.model;
/** Creates the strategy for a disappearing enemy to move in the game */
public class DisappearingEnemyMovement implements EnemyMovement {

  /**
   * Moves the enemy by implementing a bouncy movement behaviour. The enemy moves either in the y or
   * x direction, depending on which direction it is told to move in
   *
   * @param enemy The enemy that is moving, Should be a disappearing enemy
   */
  @Override
  public void move(Enemy enemy) {
    double initialXPos = enemy.getInitialXPos();
    double initialYPos = enemy.getInitialYPos();

    if (enemy.IsMovingX() == 1) {
      if (initialXPos - enemy.getXPos() > -3.5) {
        enemy.setMovementDir(1);
      } else if (initialXPos - enemy.getXPos() < -47.5) {
        enemy.setMovementDir(-1);
      }
      if (initialXPos - enemy.getXPos() > -10.5) {
        enemy.setMustDisappear(false);
      } else if (initialXPos - enemy.getXPos() < -37.5) {
        enemy.setMustDisappear(false);
      } else {
        enemy.setMustDisappear(true);
      }
      if (enemy.getMovementDir() == 1) {
        enemy.setXPos(enemy.getXPos() + 0.25);
      } else if (enemy.getMovementDir() == -1) {
        enemy.setXPos(enemy.getXPos() - 0.25);
      }

    } else if (enemy.IsMovingY() == 1) {
      if (initialYPos - enemy.getYPos() > -3.5) {
        enemy.setMovementDir(1);
      } else if (initialYPos - enemy.getYPos() < -47.5) {
        enemy.setMovementDir(-1);
      }
      if (initialYPos - enemy.getYPos() > -10.5) {
        enemy.setMustDisappear(false);
      } else if (initialYPos - enemy.getYPos() < -37.5) {
        enemy.setMustDisappear(false);
      } else {
        enemy.setMustDisappear(true);
      }
      if (enemy.getMovementDir() == 1) {
        enemy.setYPos(enemy.getYPos() + 0.25);
      } else if (enemy.getMovementDir() == -1) {
        enemy.setYPos(enemy.getYPos() - 0.25);
      }
    }
  }
}
