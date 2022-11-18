package stickman.model;
/** Represents a character in the game. A game can only have one character */
import java.util.ArrayList;

public class CharacterImpl extends Character {
  private Layer characterLayer;
  private double height;
  private double width;
  private double xPos;
  private double yPos;
  private String imagePath;
  private ArrayList<Life> life;
  private String type;
  private boolean mustDisappear;
  private double initialX;
  public boolean isJumping;
  public boolean isMovingLeft;
  public boolean isMovingRight;
  public boolean isMovingDown;
  public boolean hasStopped;
  private CharacterState state;
  private Subject subject;
  private boolean lostALife;
  private boolean hitAnEnemy;
  private Entity isOnPlatform;

  CharacterImpl(String size, double xPos, double levelHeight, int characterLifeCount, Level level) {
    isJumping = false;
    isMovingRight = false;
    isMovingLeft = false;
    isMovingDown = false;
    hasStopped = true;
    this.state = new CharacterContinue();
    this.lostALife = false;
    this.initialX = xPos;
    this.subject = level;
    life = new ArrayList<Life>(characterLifeCount);
    for (int i = 0; i < characterLifeCount; i++) {
      life.add(new Life(this.getInitialX() * (i + 1)));
    }
    type = "Dynamic";
    this.isOnPlatform = null;
    this.height = CharacterFactory.make(size).getHeight();
    this.width = CharacterFactory.make(size).getWidth();
    this.xPos = xPos;
    this.yPos = levelHeight - this.height;
    this.characterLayer = Layer.FOREGROUND;
    this.imagePath = "ch_stand1.png";
    this.mustDisappear = false;
    this.hitAnEnemy = false;
  }

  /**
   * Tells the view the image path for the character at the current moment in time (this means the
   * image can change, enabling simple gif-like animation)
   *
   * @return The path of the image where resources/ is the relative root (e.g. "example.png" will
   *     look for resources/example.png)
   */
  @Override
  public String getImagePath() {
    return this.imagePath;
  }

  /**
   * Tells the view the horizontal position of the hero so the camera can follow.
   *
   * @return The current x position of the hero in the same format as everything else (number of
   *     pixels).
   */
  @Override
  public double getXPos() {
    return this.xPos;
  }

  /**
   * Updates the character's current x position with the given parameter
   *
   * @param newX character's new x position Should be a non-negative double using the top left hand
   *     corner as 0,0 and increasing as the position moves right
   */
  @Override
  public void setXPos(double newX) {
    this.xPos = newX;
  }

  /**
   * Tells the view the vertical position of the hero
   *
   * @return The current y position of the hero in the same format as everything else (number of
   *     pixels).
   */
  @Override
  public double getYPos() {
    return this.yPos;
  }

  /**
   * Updates the character's current y position with the given parameter
   *
   * @param newY character's new y position Should be a non-negative double using the top left hand
   *     corner as 0,0 and increasing as the position moves down
   */
  @Override
  public void setYPos(double newY) {
    this.yPos = newY;
  }

  /**
   * Gives the initial x position of the character (at the time of creation)
   *
   * @return character's initial x position a non-negative double using the top left hand corner as
   *     0,0 and increasing as the position moves right
   */
  @Override
  public double getInitialX() {
    return this.initialX;
  }

  /**
   * Tells the view how tall to draw the given character (can change over time)
   *
   * @return The height in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Tells the view how wide to draw the given entity (can change over time)
   *
   * @return The width in co-ordinate space (effectively number of pixels)
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * Tells the view what Z-order to draw the character on - to the back, or to the front. The order
   * within each layer is undefined.
   *
   * @return The layer to draw the entity on.
   */
  @Override
  public Layer getLayer() {
    return characterLayer;
  }

  /**
   * Returns true if the character must disappear and false otherwise
   *
   * @return The boolean indicating whether the character must disappear or not
   */
  @Override
  public boolean mustDisappear() {
    return this.mustDisappear;
  }

  /**
   * Updates whether the character should go hidden using the given parameter
   *
   * @param mustDisappear character's new mustDisappear value Should be true if it should go hidden
   *     and false otherwise
   */
  @Override
  public void setMustDisappear(boolean mustDisappear) {
    this.mustDisappear = mustDisappear;
  }

  /**
   * Called to indicate that the character is standing on a platform or not. Returns true if he is
   * standing on a platform and false otherwise. Used to adjust character's y position by moving the
   * character down from a platform when needed
   *
   * @return Whether the character is standing on the platform or not
   */
  @Override
  public Entity getIsOnPlatform() {
    return this.isOnPlatform;
  }

  /**
   * Called from LevelImpl to mark the character as being on the platform or not.
   *
   * @param isOnPlatform The boolean used to determine character's state. True if the character is
   *     on the platform and false otherwise
   */
  @Override
  public void setIsOnPlatform(Entity isOnPlatform) {
    this.isOnPlatform = isOnPlatform;
  }
  /**
   * Handles all entity's collisions with the characterImpl. Since the characterImpl cannot collide
   * with itself, this method does nothing
   *
   * @param character The characterImpl itself
   */
  @Override
  public void handleCollision(Character character) {}

  /**
   * Sets the subject (LevelImpl class) for the character to observe and update its state
   * accordingly (observer design pattern)
   *
   * @param subject The subject to be observed by the character and notify it if it is modified
   */
  @Override
  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  /**
   * Updates the state of the character based on changes in the subject (which is the LevelImpl) Can
   * have one of the following states: CharacterContinue, CharacterWon, CharacterLost,
   * CharacterReconfigure
   */
  @Override
  public void update() {
    String message = this.subject.getUpdate(this);
    state.change(this, message);
  }

  /**
   * Chanages the state of the character to the given parameter
   *
   * @param state The state to which the character should change to Must be one of the followings:
   *     CharacterContinue, CharacterReconfigure, CharacterWon, CharacterLost
   */
  @Override
  public void setState(CharacterState state) {
    this.state = state;
  }

  /**
   * Returns true if the character has hit an enemy from above Otherwise, returns false
   *
   * @return The boolean indicating whether the character has hit an enemy from above or not
   */
  @Override
  public boolean getHitAnEnemy() {
    return this.hitAnEnemy;
  }

  /**
   * Changes whether the character has hit an enemy from above based on the given parameter
   *
   * @param hitAnEnemy CharacterImpl's new hitAnEnemy value Should be true if it has hit an enemy
   *     from above and false otherwise
   */
  @Override
  public void setHitAnEnemy(boolean hitAnEnemy) {
    this.hitAnEnemy = hitAnEnemy;
  }

  /**
   * Tells which state the character is at - CharacterContinue, CharacterReconfigure, CharacterWon,
   * and CharacterLost are four possible states for the character
   *
   * @return The state of the character it is currently at
   */
  @Override
  public CharacterState getState() {
    return this.state;
  }

  /**
   * Called when the hero is told to jump. If the hero is in the middle of the air nothing changes
   * and returns false otherwise, the hero is marked as having started jumping and returns true
   *
   * @return If the jump has been successful or not
   */
  @Override
  public boolean jump() {
    hasStopped = false;
    if (isJumping) {
      return false;
    }
    isJumping = true;
    // isMovingDown = false;
    return true;
  }

  /**
   * Called when the hero is told to go left. If the hero is at x position 0, nothing changes and
   * returns false otherwise, the hero is marked as having started moving left and returns true
   *
   * @return If the movement to left has been successful or not
   */
  @Override
  public boolean moveLeft() {
    isMovingRight = false;
    if (xPos == 0) {
      isMovingLeft = false;
      hasStopped = true;
      return false;
    }

    hasStopped = false;
    isMovingLeft = true;
    return true;
  }

  /**
   * Called when the hero is told to go down. the hero is marked as having started moving down and
   * returns true
   *
   * @return If the downward movement has been successful or not
   */
  @Override
  public boolean moveDown() {
    isMovingDown = true;
    // isJumping = false;
    return true;
  }

  /**
   * Called when the character wants to stop moving down only (eg- when he has reached the floor)
   *
   * @return boolean value indicating whether the downward movement has been successful
   */
  @Override
  public boolean stopMovingDown() {
    isMovingDown = false;
    // isJumping = true;
    return true;
  }

  /**
   * Called when the hero is told to go right. the hero is marked as having started moving left and
   * returns true
   *
   * @return If the movement to right has been successful or not
   */
  @Override
  public boolean moveRight() {
    // xPos += 1;
    isMovingRight = true;
    isMovingLeft = false;
    hasStopped = false;
    return true;
  }

  /**
   * Called when the hero is told to stop moving. the hero is marked as having stopped all other
   * movements and returns true
   *
   * @return If the hero has successfully stopped or not
   */
  @Override
  public boolean stopMoving() {
    isMovingDown = false;
    isJumping = false;
    isMovingLeft = false;
    isMovingRight = false;
    hasStopped = true;
    return true;
  }

  /**
   * Called when the hero is told to stop moving right only the hero is marked as having stopped its
   * movement to the right and returns true
   *
   * @return If stopping the movement to the right has been successful or not
   */
  @Override
  public boolean stopMovingRight() {
    isMovingRight = false;
    return true;
  }

  /**
   * Called when the hero is told to stop moving left only the hero is marked as having stopped its
   * movement to the left and returns true
   *
   * @return If stopping the movement to the left has been successful or not
   */
  @Override
  public boolean stopMovingLeft() {
    isMovingLeft = false;
    return true;
  }

  /**
   * Changes the image path to the character with the given parameter - this must be a valid photo
   * in the resources
   *
   * @param imagePath The new image path to the character as a String
   */
  @Override
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  /**
   * Returns the remaining lives of the character as an arrayList of Life objects
   *
   * @return character's remaining lives
   */
  @Override
  public ArrayList<Life> getLife() {
    return this.life;
  }

  /**
   * Called when the character has lost a life - eg hitting an enemy from sides ot bottom Reduces
   * his lives by one and indicates that he has hit an enemy in the last movement
   */
  @Override
  public void loseALife() {
    this.life.remove(this.life.size() - 1);
    this.lostALife = true;
  }

  /**
   * Tells whether the character has lost a life in the last movement or not - must be true if it
   * has and false otherwise
   *
   * @return The boolean showing whether the character has lost a life
   */
  @Override
  public boolean hasLostALife() {
    return this.lostALife;
  }

  /**
   * Changes whether the character has lost a life in the last movement or not based on the
   * parameter - true if it has and false otherwise
   *
   * @param lostALife CharacterImpl's new lostALife value Should be true if it has lost a life in
   *     the last movement and false otherwise
   */
  @Override
  public void setLostALife(boolean lostALife) {
    this.lostALife = lostALife;
  }

  /**
   * Tells which entity type the character has - Dynamic (for those entities followed by the camera)
   * or Fixed for the rest
   *
   * @return CharacterImpl's type of entity as a String
   */
  @Override
  public String getEntityType() {
    return this.type;
  }

  /**
   * Called from LevelImpl to indicate whether character is moving right or not - returns true if it
   * is and false otherwise
   *
   * @return The boolean indicating whether the character is moving right
   */
  @Override
  public boolean getIsMovingRight() {
    return this.isMovingRight;
  }

  /**
   * Marks or unmarks the character as moving right using the passed parameter
   *
   * @param isMovingRight - Marks the character as moving if the passed boolean is true and unmarks
   *     it from moving to the right if the passed parameter is false
   */
  @Override
  public void setIsMovingRight(boolean isMovingRight) {
    this.isMovingRight = isMovingRight;
  }

  /**
   * Called from LevelImpl to indicate whether character is moving left or not - returns true if it
   * is and false otherwise
   *
   * @return The boolean indicating whether the character is moving left
   */
  @Override
  public boolean getIsMovingLeft() {
    return this.isMovingLeft;
  }

  /**
   * Marks or unmarks the character as moving left using the passed parameter
   *
   * @param isMovingLeft - Marks the character as moving if the passed boolean is true and unmarks
   *     it from moving to the left if the passed parameter is false
   */
  @Override
  public void setIsMovingLeft(boolean isMovingLeft) {
    this.isMovingLeft = isMovingLeft;
  }

  /**
   * Called from LevelImpl to indicate whether character is moving down or not - returns true if it
   * is and false otherwise
   *
   * @return The boolean indicating whether the character is moving down
   */
  @Override
  public boolean getIsMovingDown() {
    return this.isMovingDown;
  }

  /**
   * Marks or unmarks the character as moving down using the passed parameter
   *
   * @param isMovingDown - Marks the character as moving if the passed boolean is true and unmarks
   *     it from moving down if the passed parameter is false
   */
  @Override
  public void setIsMovingDown(boolean isMovingDown) {
    this.isMovingDown = isMovingDown;
  }

  /**
   * Called from LevelImpl to indicate whether character is jumping or not - returns true if it is
   * and false otherwise
   *
   * @return The boolean indicating whether the character is jumping
   */
  @Override
  public boolean getIsJumping() {
    return this.isJumping;
  }

  /**
   * Marks or unmarks the character as jumping using the passed parameter
   *
   * @param isJumping - Marks the character as jumping if the passed boolean is true and unmarks
   *     it from jumping if the passed parameter is false
   */
  @Override
  public void setIsJumping(boolean isJumping) {
    this.isJumping = isJumping;
  }
}
