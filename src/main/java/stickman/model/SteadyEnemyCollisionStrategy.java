package stickman.model;
/** Creates the strategy for a steady enemy to handle collisions with the character */
public class SteadyEnemyCollisionStrategy implements EntityCollisionStrategy {

  /**
   * Checks if the character has collided with the enemy from above If so, the enemy will disappear
   * Otherwise, the character loses a life
   *
   * @param enemy The enemy collided with the character, must be a steady enemy
   * @param character The character collided with the enemy
   */
  @Override
  public void handleCollision(Entity enemy, Character character) {
    if (character.getYPos() + character.getHeight() > enemy.getYPos() + enemy.getHeight() /4) {

      character.loseALife();
    } else {
      // System.out.println("hi");
      character.setHitAnEnemy(true);
      enemy.setMustDisappear(true);
    }
  }
}
