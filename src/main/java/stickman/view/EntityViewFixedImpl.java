package stickman.view;
/**
 * Creates the graphic implementation of the entities that are fixed (not followed by the camera)
 */
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import stickman.model.Entity;

public class EntityViewFixedImpl implements EntityView {
  private Entity entity;
  private boolean delete = false;
  private ImageView node;
  private String imagePath;

  EntityViewFixedImpl(Entity entity) {
    this.entity = entity;
    this.imagePath = entity.getImagePath();
    this.node =
        new ImageView(imagePath); // a Node used for painting images loaded with Image class.
    this.node.setViewOrder(
        getViewOrder(entity.getLayer())); // sets the order of the entity compared to other entities
    update(0);
  }

  /**
   * Returns the view order of the graphic implementation according to the entity's layer value used
   * for drawing it
   *
   * @param layer The entity's layers - either BACKGROUND, FOREGROUND, EFFECT
   * @return The view order of the entity as a double
   */
  private double getViewOrder(Entity.Layer layer) {
    switch (layer) {
      case BACKGROUND:
        return 100.0;
      case FOREGROUND:
        return 50.0;
      case EFFECT:
        return 25.0;
      default:
        throw new IllegalStateException(
            "Javac doesn't understand how enums work so now I have to exist");
    }
  }

  /**
   * Called from game window to update the view of the entity at every moment. With Fixed entities,
   * since they are not followed by the camera, update draws them at their initial position at every
   * moment, and changes their image path if needed
   *
   * @param xViewportOffset The amount needed to update the position of the node and entity to make
   *     sure the entity will be drawn at its initial position
   */
  @Override
  public void update(double xViewportOffset) {

    String newPath = entity.getImagePath();
    if (!imagePath.equals(newPath)) {
      imagePath = newPath;
      node.setImage(new Image(imagePath));
    }
    node.setX(entity.getXPos() - xViewportOffset);
    entity.setXPos(entity.getXPos());
    node.setY(entity.getYPos());
    if (entity.mustDisappear()) {
      delete = true;
    } else {
      node.setFitHeight(entity.getHeight());
      node.setFitWidth(entity.getWidth());
      node.setPreserveRatio(true);
      delete = false;
    }
  }

  /**
   * Checks if the entity that is being drawn is the same as the entity being passed as a parameter
   * - returns true if they are the same and false otherwise
   *
   * @param entity The entity that is being passed to check its equality with the current entity
   *     being drawn
   * @return A boolean indicating whether they are the same entity or not
   */
  @Override
  public boolean matchesEntity(Entity entity) {
    return this.entity.equals(entity);
  }

  /** Indicates that the view of the entity (and not the entity itself) needs to get deleted */
  @Override
  public void markForDelete() {
    this.delete = true;
  }

  /**
   * Returns the node used to draw the entity
   *
   * @return The node used to draw the entity
   */
  @Override
  public Node getNode() {
    return this.node;
  }

  /**
   * Indicates whether the entity view is marked for deletion - returns true if it has been marked
   * and false otherwise
   *
   * @return A boolean showing whether the entity is marked for deletion
   */
  @Override
  public boolean isMarkedForDelete() {
    return this.delete;
  }
}
