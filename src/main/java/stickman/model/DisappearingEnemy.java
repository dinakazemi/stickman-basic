package stickman.model;
/**
 * Creates an instance of the disappearing enemy. Appears and disappears when moving. A disappearing
 * enemy must always move in either x or y direction A game window can have many angry enemies
 */
public class DisappearingEnemy extends Enemy {
  private String imagePath; // stores the image path of the enemy
  private double xPos; // stores its x position
  private double yPos; // stores its y position
  private double height; // stores its height
  private double width; // stores its width
  private Layer layer; // stores its entity layer
  private double isMovingX; // stores whether it is moving in the x direction
  private double isMovingY; // stores whether it is moving in the y direction
  private String type; // stores its entity type
  private boolean mustDisappear; // stores it must disappear
  private int direction; // stores its movement direction
  private double initialXPos; // stores its initial x position
  private double initialYPos; // stores its initial y position
  private EnemyMovement MovementStrategy; // stores its strategy of movement
  private EntityCollisionStrategy
      collisionStrategy; // stores its collision strategy with the character

  public DisappearingEnemy(
      double xPos,
      double yPos,
      double height,
      double width,
      double isMovingx,
      double isMovingY,
      double levelHeight) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.height = height;
    this.width = width;
    this.imagePath = "slimeBa.png"; // disappearing enemy is a blue slime
    this.layer = Layer.FOREGROUND;
    this.isMovingX = isMovingx;
    this.isMovingY = isMovingY;
    if (isMovingX == 0 && isMovingY == 0) {
      isMovingX = 1;
    }
    if (isMovingX == 1 && isMovingY == 1) {
      isMovingX = 1;
      isMovingY = 0;
    }
    this.type = "Fixed"; // it is not followed by the camera, so it is a fixed entity type
    this.mustDisappear = false; // initially, the disappearing enemy must be visible
    direction = 1; // initially it is moving in the positive direction
    initialXPos = xPos;
    initialYPos = yPos;
    MovementStrategy =
        new DisappearingEnemyMovement(); // strategy for the movement of the disappearing enemy
    collisionStrategy =
        new DisappearingEnemyCollisionStrategy(); // strategy for disappearing enemy to handle the
    // collision with character
  }

  /**
   * Tells the view the image path for the enemy at the current moment in time (this means the image
   * can change, enabling simple gif-like animation)
   *
   * @return The path of the image where resources/ is the relative root (e.g. "example.png" will
   *     look for resources/example.png)
   */
  @Override
  public String getImagePath() {
    return this.imagePath;
  }

  /**
   * Tells the view what the x position of the enemy currently is.
   *
   * @return The x position using the top left hand corner as 0,0 and increasing as the position
   *     moves right.
   */
  @Override
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Tells the view what the y position of the enemy currently is.
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
   * Tells the view how tall to draw the given enemy (can change over time)
   *
   * @return The height in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Tells the view how wide to draw the given enemy (can change over time)
   *
   * @return The width in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Tells the view what Z-order to draw the enemy on - to the back, or to the front. The order
   * within each layer is undefined.
   *
   * @return The layer to draw the entity on.
   */
  @Override
  public Layer getLayer() {
    return this.layer;
  }

  /**
   * Returns true if the enemy must disappear and false otherwise - eg when the character hits the
   * enemy from above it disappears
   *
   * @return The boolean indicating whether the enemy must disappear or not
   */
  @Override
  public boolean mustDisappear() {
    return this.mustDisappear;
  }

  /**
   * Updates whether the enemy should go hidden using the given parameter
   *
   * @param mustDisappear enemy's new mustDisappear value Should be true if it should go hidden and
   *     false otherwise
   */
  @Override
  public void setMustDisappear(boolean mustDisappear) {
    this.mustDisappear =
        mustDisappear; // if the enemy must reappear, this will be set to true, and false otherwise
  }

  /**
   * Gives the initial x position of the enemy (at the time of creation)
   *
   * @return enemy's initial x position a non-negative double using the top left hand corner as 0,0
   *     and increasing as the position moves right
   */
  @Override
  public double getInitialXPos() {
    return this.initialXPos;
  }

  /**
   * Gives the initial y position of the enemy (at the time of creation)
   *
   * @return enemy's initial y position a non-negative double using the top left hand corner as 0,0
   *     and increasing as the position moves down
   */
  @Override
  public double getInitialYPos() {
    return this.initialYPos;
  }

  /**
   * Returns true if the enemy is moving in the x direction or not
   *
   * @return is the enemy moving in the x direction or
   */
  @Override
  public double IsMovingX() {
    return this.isMovingX;
  }

  /**
   * Returns true if the enemy is moving in the y direction or not
   *
   * @return is the enemy moving in the y direction or
   */
  @Override
  public double IsMovingY() {
    return this.isMovingY;
  }

  /**
   * Returns the type of the entity that the enemy has - Dynamic (followed by the camera) or Fixed
   * (not followed)
   *
   * @return the enemy's entity type
   */
  @Override
  public String getEntityType() {
    return this.type;
  }

  /**
   * Updates the enemy's current x position with the given parameter
   *
   * @param newX enemy's new x position Should be a non-negative double using the top left hand
   *     corner as 0,0 and increasing as the position moves right
   */
  @Override
  public void setXPos(double newX) {
    this.xPos = newX;
  }

  /**
   * Returns the direction in which the enemy moves - 1 for positive and -1 for negative directions
   *
   * @return the movement direction of the enemy
   */
  @Override
  public int getMovementDir() {
    return this.direction;
  }

  /**
   * Updates the movement direction of the enemy using the given parameter
   *
   * @param newDir the new movement direction Should be 1 (for positive direction) or -1 (for
   *     negative direction)
   */
  @Override
  public void setMovementDir(int newDir) {
    this.direction = newDir;
  }

  /**
   * Updates the enemy's current y position with the given parameter
   *
   * @param newY enemy's new y position Should be a non-negative double using the top left hand
   *     corner as 0,0 and increasing as the position moves down
   */
  @Override
  public void setYPos(double newY) {
    this.yPos = newY;
  }

  /** Moves the enemy by calling the relevant movement method for angry enemy */
  @Override
  public void move() {
    MovementStrategy.move(this); // relevant movement strategy is called
  }

  /**
   * Handles this enemy's collision with the character
   *
   * @param character the character that has collided with the enemy
   */
  @Override
  public void handleCollision(Character character) {
    collisionStrategy.handleCollision(this, character); // relevant collision strategy is called
  }
}
