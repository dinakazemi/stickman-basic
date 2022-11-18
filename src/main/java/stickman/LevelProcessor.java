package stickman;
/** This class creates a LevelProcessor object that loads new levels once a level is completed. */
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import stickman.model.GameEngine;
import stickman.model.GameEngineImpl;
import stickman.model.JsonFileReader;
import stickman.view.GameWindow;

/** Responsible for handling loading levels in the game */
public class LevelProcessor {
  private Stage primaryStage;
  private int levelCounter;
  private String filePath;

  public LevelProcessor(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.levelCounter = 0;
    this.filePath = "src/main/resources/level-1.json";
  }

  /**
   * Loads the new level. Keeps track of which json level file to load by incrementing a counter
   * variable every time it is called.
   */
  public void loadLevel() {
    levelCounter += 1;
    if (levelCounter > 1) {
      primaryStage.close();
    }
    JsonFileReader jsonReader = new JsonFileReader();
    JSONObject content = jsonReader.read("src/main/resources/level-1.json");
    GameEngine model =
        new GameEngineImpl(
            jsonReader.findLevelName(content),
            jsonReader.findCharacterSize(content),
            jsonReader.findCharacterXPos(content),
            jsonReader.findCloudVelocity(content),
            jsonReader.findEnemies(content),
            jsonReader.findCharacterLifeCount(content),
            jsonReader.findPlatforms(content),
            jsonReader.findLevelLength(content));
    GameWindow window = new GameWindow(model, 640, 400, this);
    primaryStage.setScene(window.getScene());
    primaryStage.show();
    window.run();
    window.run();
  }

  /**
   * Called from game window when a level is lost. It loads the first level and set the level
   * counter equal to 1.
   */
  public void reconfigureGame() {
    levelCounter = 1;
    primaryStage.close();
    JsonFileReader jsonReader = new JsonFileReader();
    JSONObject content = jsonReader.read("src/main/resources/level-1.json");
    GameEngine model =
        new GameEngineImpl(
            jsonReader.findLevelName(content),
            jsonReader.findCharacterSize(content),
            jsonReader.findCharacterXPos(content),
            jsonReader.findCloudVelocity(content),
            jsonReader.findEnemies(content),
            jsonReader.findCharacterLifeCount(content),
            jsonReader.findPlatforms(content),
            jsonReader.findLevelLength(content));
    GameWindow window = new GameWindow(model, 640, 400, this);
    primaryStage.setScene(window.getScene());
    primaryStage.show();
    window.run();
    window.run();
  }

  /**
   * Tells GameWindow the value of levelCounter to create the proper finish message.
   *
   * @return The value of levelCounter
   */
  public int getLevelCounter() {
    return this.levelCounter;
  }
}
