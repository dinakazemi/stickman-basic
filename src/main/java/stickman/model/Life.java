package stickman.model;

/**
 * Creates an instance of the life the character has. Heart images are used to represent character's
 * lives and they move with the character A game window can contain many lives in it
 */
public class Life implements Entity {
  private String imagePath;
  private double xPos;
  private double yPos;
  private double height;
  private double width;
  private Layer layer;
  private String type;
  private boolean mustDisappear;
  private boolean reset;

  public Life(double i) {
    this.xPos = i;
    this.yPos = 0;
    this.height = 20;
    this.width = 20;
    this.layer = Layer.FOREGROUND;
    this.type = "Fixed";
    mustDisappear = false;
    this.imagePath = "heart.png";
    this.reset = false;
  }

  /**
   * Tells the view the image path for the life at the current moment in time - a hear represent
   * each life a character has
   *
   * @return The path of the image where resources/ is the relative root (e.g. "example.png" will
   *     look for resources/example.png)
   */
  @Override
  public String getImagePath() {
    return this.imagePath;
  }

  /**
   * Tells the view what the x position of the life currently is - The first life always has the
   * same x position as the character.
   *
   * @return The x position using the top left hand corner as 0,0 and increasing as the position
   *     moves right.
   */
  @Override
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Tells the view what the y position of the life currently is. All life objects have a y position
   * of 0.
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
   * Tells the view how tall to draw the given life object - each life objects has a height of 20
   *
   * @return The height in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Tells the view how wide to draw the given life object - each life objects has a width of 20
   *
   * @return The width in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Tells the view what Z-order to draw the life object on - to the back, or to the front. The
   * order within each layer is undefined - all life objects have a BACKGROUND layer
   *
   * @return The layer to draw the entity on.
   */
  @Override
  public Entity.Layer getLayer() {
    return this.layer;
  }

  /**
   * Returns the type of the entity that the life object has - Dynamic (followed by the camera) or
   * Fixed (not followed)
   *
   * @return the life object's entity type
   */
  @Override
  public String getEntityType() {
    return this.type;
  }

  /**
   * Updates the life object's current x position with the given parameter
   *
   * @param x life object's new x position. Should be a non-negative double using the top left hand
   *     corner as 0,0 and increasing as the position moves right
   */
  @Override
  public void setXPos(double x) {
    this.xPos = x;
  }

  /**
   * Returns true if the life object must disappear and false otherwise - at the current stage, the
   * life object is always unhidden
   *
   * @return The boolean indicating whether the life object must disappear or not
   */
  @Override
  public boolean mustDisappear() {
    return this.mustDisappear;
  }

  /**
   * Updates whether the life object should go hidden using the given parameter
   *
   * @param mustDisappear life object's new mustDisappear value Should be true if it should go
   *     hidden and false otherwise
   */
  @Override
  public void setMustDisappear(boolean mustDisappear) {
    this.mustDisappear = mustDisappear;
  }

  /**
   * Handles this life object's collision with the character - since the enemy and the characterImpl do
   * not collide, this method does nothing
   *
   * @param character the character that has collided with the life object
   */
  @Override
  public void handleCollision(Character character) {}
}
