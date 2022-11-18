package stickman.model;

import java.util.List;

public abstract class Level implements Subject {
  public abstract List<Entity> getEntities();

  public abstract double getHeight();

  public abstract double getWidth();

  public abstract void tick();

  public abstract double getFloorHeight();

  public abstract double getHeroX();

  public abstract boolean jump();

  public abstract boolean moveLeft();

  public abstract boolean moveRight();

  public abstract boolean stopMoving();
}
