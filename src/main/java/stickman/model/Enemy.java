package stickman.model;

public abstract class Enemy implements Entity {
  abstract void move();

  abstract double IsMovingX();

  abstract double IsMovingY();

  abstract void setYPos(double newY);

  abstract double getInitialXPos();

  abstract double getInitialYPos();

  abstract int getMovementDir();

  abstract void setMovementDir(int newDir);
}
