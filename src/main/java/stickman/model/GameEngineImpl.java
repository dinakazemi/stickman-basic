package stickman.model;
/**
 * Creates a game engine that is called from App and view It handles character movements, creation
 * of a level, and the update of the screen
 */
import stickman.view.GameWindow;
import java.util.ArrayList;
import java.util.HashMap;

public class GameEngineImpl implements GameEngine {

  private LevelImpl level;

  public GameEngineImpl(
      String levelName,
      String characterSize,
      double characterXPos,
      double cloudVelocity,
      HashMap<String, ArrayList> enemies,
      int characterLifeCount,
      HashMap<String, ArrayList> platforms,
      double levelLength) {

    level =
        new LevelImpl(
            levelName,
            characterSize,
            characterXPos,
            cloudVelocity,
            enemies,
            characterLifeCount,
            platforms,
            levelLength);
  }

  /**
   * Gives the view the level that is currently loaded
   *
   * @return the currently loaded level
   */
  @Override
  public Level getCurrentLevel() {
    return this.level;
  }

  /** Starts the level running */
  @Override
  public void startLevel() {}

  /**
   * Called when the character has to jump. Calls the jump method of the level
   *
   * @return The result of jumping - true if jump successfully worked for level and false otherwise
   */
  @Override
  public boolean jump() {
    return level.jump();
  }

  /**
   * Called when the character has to move left. Calls the moveLeft method of the level
   *
   * @return The result of going left - true if moveLeft successfully worked for level and false
   *     otherwise
   */
  @Override
  public boolean moveLeft() {
    return level.moveLeft();
  }

  /**
   * Called when the character has to go right. Calls the moveRight method of the level
   *
   * @return The result of going right - true if moveRight successfully worked for level and false
   *     otherwise
   */
  @Override
  public boolean moveRight() {
    return level.moveRight();
  }

  /**
   * Called when the character has to stop moving. Calls the stopMoving method of the level
   *
   * @return The result of stopping movement - true if stopMove successfully worked for level and
   *     false otherwise
   */
  @Override
  public boolean stopMoving() {
    return level.stopMoving();
  }

  /**
   * Updates the screen by calling the level's tick method if the level is not finished, otherwise
   * marks the game window as being finished
   *
   * @param gameWindow The game window that is currently loaded
   */
  @Override
  public void tick(GameWindow gameWindow) {

    if (level.isFinished() == false && level.isLevelLost() == false) {
      level.tick();
    }
    else {
      gameWindow.setLevelFinished(true);
      double timeElapsed = level.getTimeElapsed();
      if (level.isFinished()){
        gameWindow.loadNewLevel(timeElapsed);
      }
      else if (level.isLevelLost()){
        gameWindow.reconfigureGame();
      }
    }
  }
}
