package stickman.model;

/** Creates the strategy for an angry enemy to handle collisions with the character */
public class AngryEnemyCollisionStrategy implements EntityCollisionStrategy {

  /**
   * Checks if the character has collided with the enemy from above If so, the enemy will disappear
   * Otherwise, the character loses a life
   *
   * @param enemy The enemy collided with the character, must be an angry enemy
   * @param character The character collided with the enemy
   */
  @Override
  public void handleCollision(Entity enemy, Character character) {

    if (character.getYPos() + character.getHeight() > enemy.getYPos() + enemy.getHeight() / 4) {
      character.loseALife();
    }
    // otherwise, the characterImpl has collided from above and the enemy will disappear
    else {
      character.setHitAnEnemy(true);
      enemy.setMustDisappear(true);
    }
  }
}
