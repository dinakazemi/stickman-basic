package stickman.model;
/**
 * Creates an instance of the steady enemy. A steady enemy never moves A game window can have many
 * steady enemies
 */
public class SteadyEnemy extends Enemy {

  private String imagePath;
  private double xPos;
  private double yPos;
  private double height;
  private double width;
  private Layer layer;
  private String type;
  private boolean mustDisappear;
  private double initialXPos;
  private double initialYPos;
  private EnemyMovement MovementStrategy;
  private int direction;
  private EntityCollisionStrategy collisionStrategy;

  public SteadyEnemy(double xPos, double yPos, double height, double width, double levelHeight) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.height = height;
    this.width = width;
    this.imagePath = "slimeGa.png";
    this.layer = Layer.FOREGROUND;
    this.type = "Fixed";
    this.mustDisappear = false;
    this.initialXPos = xPos;
    this.initialYPos = yPos;
    this.MovementStrategy = new SteadyEnemyMovement();
    this.direction = 1;
    this.collisionStrategy = new SteadyEnemyCollisionStrategy();
    // System.out.println(height);
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

  /** Moves the enemy by calling the relevant movement method for angry enemy */
  @Override
  public void move() {
    MovementStrategy.move(this);
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
    this.mustDisappear = mustDisappear;
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
    return 0;
  }

  /**
   * Returns true if the enemy is moving in the y direction or not
   *
   * @return is the enemy moving in the y direction or
   */
  @Override
  public double IsMovingY() {
    return 0;
  }

  /**
   * Updates the enemy's current y position with the given parameter
   *
   * @param newY enemy's new y position Should be a non-negative double using the top left hand
   *     corner as 0,0 and increasing as the position moves down
   */
  @Override
  public void setYPos(double newY) {}

  /**
   * Handles this enemy's collision with the character
   *
   * @param character the character that has collided with the enemy
   */
  @Override
  public void handleCollision(Character character) {
    collisionStrategy.handleCollision(this, character);
  }


}
