package stickman.model;

public interface Entity {
  String getImagePath();

  double getXPos();

  double getYPos();

  double getHeight();

  double getWidth();

  Layer getLayer();

  String getEntityType();

  void setXPos(double x);

  enum Layer {
    BACKGROUND,
    FOREGROUND,
    EFFECT
  }

  boolean mustDisappear();

  void setMustDisappear(boolean mustDisappear);

  void handleCollision(Character character);
}
