package stickman.model;
/**
 * Creates a level and handles the methods related to a level . A game window can have only one
 * level loaded at a time. The level is observed as a subject by the CharacterImpl
 */
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class LevelImpl extends Level {
  private double width;
  private double height;
  private Character character;
  private ArrayList<Cloud> clouds;
  private double floorHeight;
  private ArrayList<Platform> platforms;
  private double counter;
  private int jump_timer;
  private List<Double> cloudInitialXPos;
  private String levelName;
  private ArrayList<Enemy> enemies;
  private ArrayList<Entity> surroundingEntites;
  private double length;
  private boolean isFinished;
  private ArrayList<Observer> observers;
  private String message;
  private boolean chnaged;
  private ArrayList<Enemy> initialEnemies;
  private boolean levelLost;
  private double timeElapsed;
  private HashMap<String, ArrayList> enemiesInfo;

  LevelImpl(
      String levelName,
      String characterSize,
      double characterXPos,
      double cloudVelocity,
      HashMap<String, ArrayList> enemiesInfo,
      int characterLifeCount,
      HashMap<String, ArrayList> platforms,
      double length) {
    this.timeElapsed = 0;
    this.height = 400;
    this.width = 640;
    this.floorHeight = 300;
    this.clouds = new ArrayList<Cloud>();
    this.jump_timer = 0;
    this.cloudInitialXPos = new ArrayList<Double>();
    this.surroundingEntites = new ArrayList<Entity>();
    this.platforms = new ArrayList<Platform>();
    this.length = length;
    this.isFinished = false;
    this.observers = new ArrayList<Observer>();
    this.message = "Continue";
    this.chnaged = false;
    this.levelLost = false;
    this.enemiesInfo = enemiesInfo;

    for (int i = 0; i < 15; i++) {
      this.clouds.add(new Cloud(cloudVelocity, this.floorHeight, length));
      this.cloudInitialXPos.add(clouds.get(i).getXPos());
    }
    this.counter = 0;
    this.levelName = levelName;
    this.enemies = EnemyFactory.make(enemiesInfo, this.getFloorHeight());
    this.platforms = PlatformFactory.make(platforms, this.getFloorHeight());
    this.character =
        new CharacterImpl(characterSize, characterXPos, this.floorHeight, characterLifeCount, this);
    this.observers.add(character);
    // this.initialEnemies = EnemyFactory.make(enemiesInfo, this.getFloorHeight());
  }

  /**
   * Returns all the entities present in the level right now to the veiw to draw
   *
   * @return The list of all entities in this level
   */
  public List<Entity> getEntities() {

    List<Entity> levelEntities = new ArrayList<Entity>();
    levelEntities.add(character);
    for (Cloud cloud : clouds) {
      levelEntities.add(cloud);
    }
    // adding hearts to the left top side of the game showing how many lives the characterImpl has left.
    for (Enemy e : enemies) {
      levelEntities.add(e);
    }
    levelEntities.addAll(platforms);
    levelEntities.addAll(character.getLife());
    return levelEntities;
  }

  /**
   * Adds an observer to watch level as its subject and get updates on level
   *
   * @param observer The new observer to watch level
   */
  @Override
  public void register(Observer observer) {
    this.observers.add(observer);
  }

  /**
   * Removes an observer from watching the level and getting updates on it
   *
   * @param observer The observer to be removed
   */
  @Override
  public void unregister(Observer observer) {
    this.observers.remove(observer);
  }

  @Override
  public void notifyObservres() {
    for (Observer o : observers) {
      o.update();
    }
  }

  /**
   * Updates the level as a subject with a new message and notifies observers of the update. Used
   * when a certain event occurs in level and changes become necessary - eg when characterImpl loses
   *
   * @param newMessage The updated message
   */
  public void postMessage(String newMessage) {
    this.message = newMessage;
    this.chnaged = true;
    notifyObservres();
  }

  public double getTimeElapsed() {
    return this.timeElapsed;
  }

  /**
   * Returns the message that the level contains to help determine whether it has been updated or
   * not
   *
   * @param observer The observer that will get the message
   * @return The message that level contains
   */
  @Override
  public String getUpdate(Observer observer) {
    return this.message;
  }

  /**
   * The height of the level (not necessarily the height of the view)
   *
   * @return The height that level has
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * The width of the level (almost definitely not the width of the view)
   *
   * @return The width that level has
   */
  @Override
  public double getWidth() {

    return this.width;
  }

  /**
   * This is the way the view tells the model to update - it is the trigger that is sent to the
   * model each frame. Movement of all the moving entities, handling the collision of entities with
   * the character and changing the state of the character accordingly are handled inside this
   * method. This method also keeps track of the time elapsed and if the character wins the game, it
   * will print it out on the screen
   */
  @Override
  public void tick() {
    // every time tick is called we delete old elements add new elements to surroundingElements to
    // make sure it is up to date.
    surroundingEntites.clear();
    character.setLostALife(false);
    character.setHitAnEnemy(false);
    // collidedEntities.clear();
    timeElapsed += 0.017;
    if (character.getIsMovingRight()) {
      character.setXPos(character.getXPos() + 1);
      for (int i = 0; i < character.getLife().size(); i++) {
        character.getLife().get(i).setXPos(character.getLife().get(i).getXPos() + 1);
      }
      if (counter == 0) {
        character.setImagePath("ch_stand1.png");
      } else if (counter == 1) {
        character.setImagePath("ch_walk1.png");
      } else if (counter == 2) {
        character.setImagePath("ch_walk2.png");
      } else if (counter == 3) {
        character.setImagePath("ch_walk3.png");
      } else if (counter == 4) {
        character.setImagePath("ch_walk4.png");
      }
    }
    if (character.getIsMovingLeft()) {
      if (character.getXPos() >= 1) {
        character.setXPos(character.getXPos() - 1);
        for (int i = 0; i < character.getLife().size(); i++) {
          character.getLife().get(i).setXPos(character.getLife().get(i).getXPos() - 1);
        }
        if (counter == 0) {
          character.setImagePath("ch_stand4.png");
        } else if (counter == 1) {
          character.setImagePath("ch_walk5.png");
        } else if (counter == 2) {
          character.setImagePath("ch_walk6.png");
        } else if (counter == 3) {
          character.setImagePath("ch_walk7.png");
        } else if (counter == 4) {
          character.setImagePath("ch_walk8.png");
        }
      } else {
        character.setXPos(0);
        if (counter == 0.0) {
          character.setImagePath("ch_stand4.png");
        } else if (counter == 1.0) {
          character.setImagePath("ch_walk5.png");
        } else if (counter == 2.0) {
          character.setImagePath("ch_walk6.png");
        } else if (counter == 3.0) {
          character.setImagePath("ch_walk7.png");
        } else if (counter == 4.0) {
          character.setImagePath("ch_walk8.png");
        }
      }
    }

    if (character.getIsJumping()) {
      if (jump_timer <= 30) {
        character.setYPos(character.getYPos() - 3);
        jump_timer += 1;
      } else if (jump_timer <= 61) {
        character.setYPos(character.getYPos() + 3);
        jump_timer += 1;
      } else {
        jump_timer = 0;
        character.setIsJumping(false);
      }
    }

    if (character.getIsMovingDown()) {
      // System.out.println(characterImpl.getXPos() + " " + characterImpl.getYPos());
      if (character.getYPos() >= this.getFloorHeight() - character.getHeight()) {
        if (character.getIsJumping()){

        }
        else{
          character.stopMovingDown();
        }

        // counter = 0;
        // characterImpl.setImagePath("ch_stand1.png");
        // characterImpl.setYPos(this.getFloorHeight() - characterImpl.getHeight());
        character.setIsOnPlatform(null);
      } else {
        character.setYPos(character.getYPos() + 3);
      }
    }

    if (counter == 5.0) {
      counter = 0;
    }
    counter += 0.125;

    for (Enemy e : enemies) {
      e.move();
    }

    //      implementing cloud movement in the level
    for (int i = 0; i < clouds.size(); i++) {
      Cloud c = clouds.get(i);
      c.setCloudDirection(1);
      c.move();
    }

    surroundingEntites.addAll(enemies);
    surroundingEntites.addAll(platforms);

    for (Entity e : surroundingEntites) {
      if (hasCollided(e, character)) {
        // System.out.println(e + " has collided");
        // System.out.println(e + " " + characterImpl.getXPos() + " " + characterImpl.getYPos());
        e.handleCollision(character);
        if (character.hasLostALife()) {
          this.postMessage("Reconfigure");
          this.initialEnemies = EnemyFactory.make(enemiesInfo, this.getFloorHeight());
          enemies = initialEnemies;
          break;
        } else if (character.getHitAnEnemy()) {
          this.postMessage("Continue");
          enemies.remove(e);
          break;
        }
      }
      //      if (characterImpl.getIsOnPlatform() && hasCollided(e, characterImpl) == false){
      //        characterImpl.moveDown();
      //      }
      if ((character.getIsOnPlatform() == e && character.getXPos() > e.getXPos() + e.getWidth())
          || (character.getIsOnPlatform() == e && character.getXPos() < e.getXPos())) {
        // System.out.println(characterImpl.isJumping + " " + characterImpl.getXPos() + " " + e);
        if (character.getIsJumping()){

        }
        else{
          character.moveDown();
        }

        //        if (characterImpl.getYPos() >= this.getFloorHeight() - characterImpl.getHeight()){
        //          characterImpl.setYPos(this.getFloorHeight() - characterImpl.getHeight());
        //          characterImpl.stopMovingDown();
        //        }
      }
    }

    if (character.getXPos() == length) {
      this.postMessage("Won");
      System.out.println("Time elapsed: " + Math.round(timeElapsed * 100.0) / 100.0);
      this.isFinished = true;
    }
    if (character.getLife().size() == 0) {
      this.postMessage("Lost");
      this.levelLost = true;
    }
    character.getState().updateScreen(character);

    if (character.getXPos() == character.getInitialX()) {
      this.postMessage("Continue");
    }
  }

  /**
   * Tells the game engine whether the character has lost the level or not. A level is lost if the
   * character has lost all his lives
   *
   * @return A boolean indicating whether the game is lost
   */
  public boolean isLevelLost() {
    return this.levelLost;
  }
  /**
   * Tells the game engine whether the level has been finished or not A level is finished if the
   * character wins the game - returns true if it has been finished and false otherwise
   *
   * @return A boolean indicating whether the game has been finished
   */
  public boolean isFinished() {
    return this.isFinished;
  }

  /**
   * Checks whether an entity has collided with the character or not - returns true if it has and
   * false otherwise
   *
   * @param e The entity that its collision with the character is determined
   * @param character The character in the game
   * @return The boolean indicating whether a collision has happened or not
   */
  private boolean hasCollided(Entity e, Character character) {
    return character.getXPos() < e.getXPos() + e.getWidth()
        && character.getXPos() + character.getWidth() > e.getXPos()
        && character.getYPos() < e.getYPos() + e.getHeight()
        && character.getYPos() + character.getHeight() > e.getYPos();
  }

  /**
   * Tells the view where the ground stops and the sky starts
   *
   * @return the height of the floor in the level as a double
   */
  @Override
  public double getFloorHeight() {

    return floorHeight;
  }

  /**
   * Tells the view the horizontal position of the hero so the camera can follow.
   *
   * @return The x position of the hero
   */
  @Override
  public double getHeroX() {

    return character.getXPos();
  }

  /**
   * Tells the hero to jump by calling character's jump method
   *
   * @return Whether the jump has been successful or not - true if it has and false otherwise
   */
  @Override
  public boolean jump() {

    boolean result = character.jump();
    return result;
  }

  /**
   * Tells the hero to start moving left by calling character's moveLeft method
   *
   * @return Whether movement to the left has been successful or not - true if it has and false
   *     otherwise
   */
  @Override
  public boolean moveLeft() {

    boolean result = character.moveLeft();
    return result;
  }

  /**
   * Tells the hero to start moving right by calling character's moveRight method
   *
   * @return Whether movement to the right has been successful or not - true if it has and false
   *     otherwise
   */
  @Override
  public boolean moveRight() {

    boolean result = character.moveRight();
    return result;
  }

  /**
   * Tells the hero to stop moving left or right by calling relevant methods in characterImpl.
   *
   * @return Whether stopping the movement has been successful or not - true if it has and false
   *     otherwise
   */
  @Override
  public boolean stopMoving() {
    counter = 0;
    if (character.getIsMovingLeft()) {
      character.setImagePath("ch_stand4.png");
    }
    if (character.getIsMovingRight()) {
      character.setImagePath("ch_stand1.png");
    }
    if (jump_timer > 0) {
      if (character.getIsMovingRight()) {
        // counter = 0;
        character.stopMovingRight();
      } else if (character.getIsMovingLeft()) {
        // counter = 0;
        character.stopMovingLeft();
      }
    } else if (jump_timer == 0) {
      character.stopMoving();
    }
    return true;
  }

  /**
   * Returns the length of the level (the x position at which the level is completed)
   *
   * @return the length (finish line x position) of the level
   */
  public double getLength() {
    return this.length;
  }
}
