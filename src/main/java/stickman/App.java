package stickman;
/** Runs the application by creating a game engine and a game window */
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import stickman.model.GameEngine;
import stickman.model.GameEngineImpl;
import stickman.model.JsonFileReader;
import stickman.view.GameWindow;
import java.util.Map;

public class App extends Application {

  /**
   * Program's entry point Calls the start method to start the game
   *
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  /** Creates a new new stage and calls level processor to load levels from the json file */
  public void start(Stage primaryStage) {
    Map<String, String> params = getParameters().getNamed();

    String s = "Java 11 sanity check";

    if (s.isBlank()) {
      throw new IllegalStateException(
          "You must be running Java 11+. You won't ever see this exception though"
              + " as your code will fail to compile on Java 10 and below.");
    }
    primaryStage.setTitle("Stickman");
    LevelProcessor levelProcessor = new LevelProcessor(primaryStage);
    levelProcessor.loadLevel();
  }
}
