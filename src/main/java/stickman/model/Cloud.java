package stickman.model;
/**
 * Creates a cloud. A game window can have many clouds A cloud can have two types that are chosen
 * randomly
 */
import java.util.Random;

public class Cloud implements Entity {
  private double velocity; // stores the velocity of the clouds
  private Layer cloudLayer; // stores the layer of cloud an entity
  private double height; // stores the height of the cloud
  private double width; // stores the width of the cloud
  private double xPos; // stores cloud's x position
  private double yPos; // stores cloud's y position
  private int cloudDirection; // stores the cloud's direction movement
  private String type; // store the cloud's entity type
  private boolean mustDisappear; // stores whether the cloud can disappear or not

  Cloud(double velocity, double levelHeight, double levelFinalX) {
    type = "Fixed"; // sets the cloud's entity as Dynamic
    this.cloudDirection =
        1; // sets the cloud's movement direction to 1 (as the clouds are always moving in the
    // positive x direction)
    this.velocity = velocity; // sets the velocity to the velocity given by the json file
    this.mustDisappear = false;
    // since there are two types of clouds, the program will randomly chooses which type the cloud
    // will be
    // type 1 has a background entity layer
    // type 2 has a foreground entity layer
    Random rand = new Random();
    int randomInt = rand.nextInt(2) + 1;
    if (randomInt == 1) {
      cloudLayer = Layer.BACKGROUND;
    } else {
      cloudLayer = Layer.FOREGROUND;
    }
    // choosing a random height for the cloud between 15 and 35
    double randomDouble = 15 + (35 - 15) * rand.nextDouble();
    this.height = randomDouble;
    // choosing a random width for the cloud
    randomDouble = 20 + (40 - 20) * rand.nextDouble();
    this.width = randomDouble;
    // choosing a random x position for the cloud that is between 0 and the maximum x in the level
    // (which is the a position at which the level is complete)
    randomDouble = 0 + (levelFinalX - 0) * rand.nextDouble();
    this.xPos = randomDouble;
    // choosing a random y position between the floorHeight-80 and 0 (so that the clouds do not
    // collide with the floor or the character)
    randomDouble = 0 + (levelHeight - 80 - 0) * rand.nextDouble();
    this.yPos = randomDouble;
  }

  /**
   * Tells the view the image path for the cloud at the current moment in time (this means the image
   * can change, enabling simple gif-like animation) A cloud can have two different image path
   * according to their type
   *
   * @return The path of the image where resources/ is the relative root (e.g. "example.png" will
   *     look for resources/example.png)
   */
  @Override
  public String getImagePath() {
    // if the cloud is of type 1 choose this image path
    if (cloudLayer == Layer.BACKGROUND) {
      return "cloud_1.png";
    }
    // if the cloud is of type 2 choose this image path
    else {
      return "cloud_2.png";
    }
  }

  /**
   * Tells the view what the x position of the cloud currently is - x position is a random double
   * between 0 and level length
   *
   * @return Cloud's current x position
   */
  @Override
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Tells the view what the y position of the cloud currently is - can have a random y position
   * between 0 and level's floor height - 80
   *
   * @return Cloud's current y position
   */
  @Override
  public double getYPos() {
    return this.yPos;
  }

  /**
   * Tells the view how tall to draw the given cloud (can change over time) - can have a random
   * height between 15 and 35
   *
   * @return the height of the cloud
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Tells the view how wide to draw the given cloud (can change over time) - can have a random
   * width between 20 and 40
   *
   * @return the width of the cloud
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Tells the view what Z-order to draw the cloud on - to the back, or to the front.
   *
   * @return the layer (the Z-order) of the cloud
   */
  @Override
  public Layer getLayer() {
    return cloudLayer;
  }

  /**
   * Changes the x position of the cloud according to the given parameter
   *
   * @param newX the new x position of the cloud - must be a non-negative double
   */
  @Override
  public void setXPos(double newX) {
    this.xPos = newX;
  }

  /**
   * Tells the cloud to move along the x axis (in the positive direction) according to its velocity
   */
  public void move() {
    double xIncrement = this.cloudDirection * velocity * 0.017;
    this.setXPos(this.getXPos() + xIncrement);
  }

  /**
   * Changes cloud's movement direction (along x axis)
   *
   * @param newDirection the new direction of movement - 1 for positive direction -1 for negative
   *     direction
   */
  public void setCloudDirection(int newDirection) {
    this.cloudDirection = newDirection;
  }

  /**
   * Tells view the type of entity a cloud has - Dynamic (followed by the camera) or Fixed (not
   * followed)
   *
   * @return cloud's entity type
   */
  @Override
  public String getEntityType() {
    return this.type;
  }

  /**
   * Returns true if the cloud must disappear and false otherwise
   *
   * @return The boolean indicating whether the cloud must disappear or not
   */
  public boolean mustDisappear() {
    return this.mustDisappear;
  }

  /**
   * Updates whether the cloud should go hidden using the given parameter
   *
   * @param mustDisappear cloud's new mustDisappear value Should be true if it should go hidden and
   *     false otherwise
   */
  @Override
  public void setMustDisappear(boolean mustDisappear) {
    this.mustDisappear = mustDisappear;
  }

  /**
   * Handles this cloud's collision with the character - The method does nothing as clouds and
   * character do not collide,
   *
   * @param character the character that has collided with the cloud
   */
  @Override
  public void handleCollision(Character character) {}
}
