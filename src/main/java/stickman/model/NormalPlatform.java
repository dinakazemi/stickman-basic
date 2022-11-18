package stickman.model;
/**
 * Creates an instance of the normal platform. A normal platform cannot move. A game window can have
 * many normal platform
 */
public class NormalPlatform extends Platform {
  private double xPos;
  private double yPos;
  private double height;
  private double width;
  private String imagePath;
  private Layer platformLayer;
  private String type;
  private double levelHeight;
  private EntityCollisionStrategy collisionStrategy;

  NormalPlatform(double xPos, double yPos, double height, double width, double levelHeight) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.height = height;
    this.width = width;
    this.platformLayer = Layer.FOREGROUND;
    this.imagePath = "normal_platform.png";
    this.type = "Fixed";
    this.levelHeight = levelHeight;
    collisionStrategy = new NormalPlatformCollisionStrategy();
  }

  /**
   * Tells the view the image path for the platform at the current moment in time
   *
   * @return The path of the image where resources/ is the relative root (e.g. "example.png" will
   *     look for resources/example.png)
   */
  @Override
  public String getImagePath() {
    return this.imagePath;
  }

  /**
   * Tells the view what the x position of the platform currently is.
   *
   * @return The x position using the top left hand corner as 0,0 and increasing as the position
   *     moves right.
   */
  @Override
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Tells the view what the y position of the platform currently is.
   *
   * @return The y position using the top left hand corner as 0,0 and increasing as the position
   *     moves DOWN (JavaFX does not use the standard mathematics axes with the origin in the bottom
   *     left)
   */
  @Override
  public double getYPos() {
    return this.yPos;
  }

  /**
   * Tells the view how tall to draw the given platform (can change over time)
   *
   * @return The height in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Tells the view how wide to draw the given platform (can change over time)
   *
   * @return The width in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Tells the view what Z-order to draw the platform on - to the back, or to the front. The order
   * within each layer is undefined.
   *
   * @return The layer to draw the entity on.
   */
  @Override
  public Layer getLayer() {
    return this.platformLayer;
  }

  /**
   * Returns the type of the entity that the platform has - Dynamic (followed by the camera) or
   * Fixed (not followed)
   *
   * @return the platform's entity type
   */
  @Override
  public String getEntityType() {
    return this.type;
  }

  /**
   * Updates the platform's current x position with the given parameter
   *
   * @param newX enemy's new x position Should be a non-negative double using the top left hand
   *     corner as 0,0 and increasing as the position moves right
   */
  @Override
  public void setXPos(double newX) {
    this.xPos = newX;
  }

  /**
   * Returns true if the platform must disappear and false otherwise
   *
   * @return The boolean indicating whether the platform must disappear or not
   */
  @Override
  public boolean mustDisappear() {
    return false;
  }

  /**
   * Updates whether the platform should go hidden using the given parameter
   *
   * @param mustDisappear platform's new mustDisappear - value Should be true if it should go hidden
   *     and false otherwise
   */
  @Override
  public void setMustDisappear(boolean mustDisappear) {}

  /**
   * Handles this platform's collision with the character
   *
   * @param character The character that has collided with the platform
   */
  public void handleCollision(Character character) {
    collisionStrategy.handleCollision(this, character);
  }


}
