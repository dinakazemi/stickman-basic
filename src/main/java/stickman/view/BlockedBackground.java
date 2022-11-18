package stickman.view;
/** Creates a background for the game window */
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import stickman.model.GameEngine;

public class BlockedBackground implements BackgroundDrawer {
  private Rectangle sky;
  private Rectangle floor;
  private Pane pane;
  private GameEngine model;

  /**
   * Tells the game window to draw the background by drawing a sky and a floor
   *
   * @param model The game engine that the background must interact with and get its height
   * @param pane The layout pane used draw the background
   */
  @Override
  public void draw(GameEngine model, Pane pane) {
    this.model = model;
    this.pane = pane;

    double width = pane.getWidth();
    double height = pane.getHeight();
    double floorHeight = model.getCurrentLevel().getFloorHeight();

    this.sky = new Rectangle(0, 0, width, floorHeight);
    sky.setFill(Paint.valueOf("LIGHTBLUE"));
    sky.setViewOrder(1000.0);

    this.floor = new Rectangle(0, floorHeight, width, height - floorHeight);
    floor.setFill(Paint.valueOf("GREEN"));
    floor.setViewOrder(1000.0);

    pane.getChildren().addAll(sky, floor);
  }

  /**
   * Updates the background once the character moves - Does nothing here as this is a static
   * background
   *
   * @param xViewportOffset - Indicates how much the background needs to be updated based on how
   *     much the character has moved
   */
  @Override
  public void update(double xViewportOffset) {
    // do nothing since this is a static bg
  }
}
