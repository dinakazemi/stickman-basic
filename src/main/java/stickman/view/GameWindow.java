package stickman.view;
/** Creates a game window in which the game will run */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import stickman.LevelProcessor;
import stickman.model.Entity;
import stickman.model.GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWindow {
  private final int width;
  private Scene scene;
  private Pane pane;
  private GameEngine model;
  private List<EntityView> entityViews;
  private BackgroundDrawer backgroundDrawer;
  private int levelCounter;
  private double xViewportOffset = 0.0;
  private Map<Entity, Double> initialEntityPosition;
  private static final double VIEWPORT_MARGIN = 280.0;
  private LevelProcessor levelProcessor;
  private boolean levelFinished;

  public GameWindow(GameEngine model, int width, int height, LevelProcessor levelProcessor) {
    this.model = model;
    this.pane = new Pane();
    this.width = width;
    this.scene = new Scene(pane, width, height);
    this.levelFinished = false;
    this.levelCounter = 1;
    this.entityViews = new ArrayList<>();
    this.initialEntityPosition = new HashMap<Entity, Double>();
    this.levelProcessor = levelProcessor;
    KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(model);

    scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
    scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

    this.backgroundDrawer = new ParallaxBackground();

    backgroundDrawer.draw(model, pane);
  }

  /**
   * Returns the JavaFX scene created by the game window
   *
   * @return The scene of the game window
   */
  public Scene getScene() {
    return this.scene;
  }

  /** Runs the game by updating the scene every 17 milliseconds */
  public void run() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), t -> this.draw()));

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  /**
   * marks the game window as finished, which means there is no need for it to continue updating the
   * scene
   *
   * @param isFinished A boolean indicating whether the game window must be finished or not
   */
  public void setLevelFinished(boolean isFinished) {
    this.levelFinished = isFinished;
  }

  /**
   * Checks whether the game window is marked as finished or not - returns true if it has been
   * marked and false otherwise
   *
   * @return Returns true if the game is finished and false otherwise
   */
  public boolean HasLevelFinished() {
    return this.levelFinished;
  }

  /**
   * Createsa message box informing the user that the new level has started. Called when a new level
   * is being loaded
   */
  public void createFinishMessage(double timeElapsed) {
    Alert a =
        new Alert(
            Alert.AlertType.INFORMATION,
            "Level "
                + (levelProcessor.getLevelCounter() - 1)
                + " Completed. Level "
                + levelProcessor.getLevelCounter()
                + " has started. Time elapsed: "
                + Math.round(timeElapsed * 100.00) / 100.00);
    a.show();
  }

  /**
   * Creates a message box informing the user that have lost the game and has to start from the
   * first level
   */
  public void createLostMessage() {
    Alert a = new Alert(Alert.AlertType.INFORMATION, "You have lost. Try again from level 1");
    a.show();
  }
  /**
   * Called from game engine to load a new level. Once a level is finished and calls
   * createFinishMessage to create a message informing the user that the new level has started
   */
  public void loadNewLevel(double timeElapsed) {
    levelProcessor.loadLevel();
    createFinishMessage(timeElapsed);
  }

  /**
   * Called from game engine when the character has lost to reconfigure the game from the first
   * level. It also calls createLostMessage to inform the user that the game is starting from level
   * 1.
   */
  public void reconfigureGame() {
    levelProcessor.reconfigureGame();
    createLostMessage();
  }
  /**
   * Called by the run method evey 17 milliseconds. If the game has not finished, it draws the
   * entities and updates the scene, if it has however, it will do nothing.
   */
  private void draw() {

    if (this.HasLevelFinished()) {

    } else {
      model.tick(this);
      List<Entity> entities = model.getCurrentLevel().getEntities();

      for (EntityView entityView : entityViews) {
        entityView.markForDelete();
      }

      double heroXPos = model.getCurrentLevel().getHeroX();
      heroXPos -= xViewportOffset;

      if (heroXPos < VIEWPORT_MARGIN) {
        if (xViewportOffset >= 0) {
          xViewportOffset -= VIEWPORT_MARGIN - heroXPos;
          if (xViewportOffset < 0) {
            xViewportOffset = 0;
          }
        }
      } else if (heroXPos > width - VIEWPORT_MARGIN) {
        xViewportOffset += heroXPos - (width - VIEWPORT_MARGIN);
      }
      backgroundDrawer.update(xViewportOffset);

      for (Entity entity : entities) {
        boolean notFound = true;
        for (EntityView view : entityViews) {
          if (view.matchesEntity(entity)) {
            notFound = false;
            // System.out.println (entity.getClass() + " " + view.getClass());

            view.update(xViewportOffset);

            break;
          }
        }
        if (notFound) {
          EntityView entityView;
          entityView = EntityViewFactory.make(entity, entity.getEntityType());

          entityViews.add(entityView);
          pane.getChildren().add(entityView.getNode());
        }
      }

      for (EntityView entityView : entityViews) {
        if (entityView.isMarkedForDelete()) {
          pane.getChildren().remove(entityView.getNode());
        }
      }
      entityViews.removeIf(EntityView::isMarkedForDelete);
    }
  }
}
