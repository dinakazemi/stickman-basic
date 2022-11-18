package stickman.view;

import stickman.model.Entity;

/**
 * Creates entity views according to the type of the entity passed to it. (An entity is either fixed
 * or dynamic)
 */
public class EntityViewFactory {

  /**
   * Called from game window to create an entity view based on the type of the entity passed. Entity
   * has a fixed or dynamic type.
   *
   * @param entity The entity its view we want to create (draw it)
   * @param state The type of the entity passed
   * @return The created entity view to be drawn
   */
  public static EntityView make(Entity entity, String state) {
    EntityView entityView;
    if (state.equalsIgnoreCase("fixed")) {
      entityView = new EntityViewFixedImpl(entity);
      return entityView;
    } else if (state.equalsIgnoreCase("dynamic")) {
      entityView = new EntityViewDynamicImpl(entity);
      return entityView;
    }
    return null;
  }
}
