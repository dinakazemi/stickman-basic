package stickman.model;
/** Creates the strategy for a normal to handle collisions with the character */
public class NormalPlatformCollisionStrategy implements EntityCollisionStrategy {

  /**
   * Checks if the character has collided with the platform from above If so, character will stand
   * on it. If collision happens from sides, the character moves slightly backwards.
   *
   * @param platform The platform collided with the character, must be a normal platform
   * @param character The character collided with the platform
   */
  @Override
  public void handleCollision(Entity platform, Character character) {
    //    if (characterImpl.getYPos() > platform.getYPos() + platform.getYPos()){
    //      characterImpl.moveDown();
    //    }
    if (character.getYPos() + character.getHeight()
        > platform.getYPos() + platform.getHeight() / 4) {
      if (character.getXPos() < platform.getXPos()) {
        // System.out.println("hi");
        int counter = 0;
        while (counter < 5) {
          character.setXPos(character.getXPos() - 0.5);
          for (Life l : character.getLife()) {
            l.setXPos(l.getXPos() - 0.5);
          }
          counter += 1;
        }

      } else if (character.getXPos() + character.getWidth()
          > platform.getXPos() + platform.getWidth()) {
        // System.out.println("hi");
        int counter = 0;
        while (counter < 5) {
          character.setXPos(character.getXPos() + 0.5);
          for (Life l : character.getLife()) {
            l.setXPos(l.getXPos() + 0.5);
          }
          counter += 1;
        }
      }
      // System.out.println(characterImpl.getXPos() + " " + characterImpl.getWidth() + " " +
      // platform.getXPos() + " " + platform.getWidth());
    }
    //    else if (characterImpl.getYPos() >= platform.getYPos() + platform.getHeight()){
    //      characterImpl.moveDown();
    //    }
    else {
      character.setYPos(platform.getYPos() - character.getHeight());
      character.setIsOnPlatform(platform);
      character.stopMovingDown();
    }
  }
}
