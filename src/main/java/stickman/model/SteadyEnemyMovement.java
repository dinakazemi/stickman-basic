package stickman.model;
/** Creates the strategy for a steady enemy to move in the game */
public class SteadyEnemyMovement implements EnemyMovement {

  /**
   * Since the steady enemy does not move, this method does not move the enemy
   *
   * @param enemy the enemy we (don't) want to move, must be a steady enemy
   */
  @Override
  public void move(Enemy enemy) {}
}
