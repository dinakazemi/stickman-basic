package stickman.model;

import java.util.ArrayList;

public abstract class Character implements Entity, Observer {
  abstract void setYPos(double newY);

  abstract double getInitialX();

  abstract Entity getIsOnPlatform();

  abstract void setIsOnPlatform(Entity isOnPlatform);

  abstract void setState(CharacterState state);

  abstract boolean getHitAnEnemy();

  abstract void setHitAnEnemy(boolean hitAnEnemy);

  abstract CharacterState getState();

  abstract boolean jump();

  abstract boolean moveLeft();

  abstract boolean moveDown();

  abstract boolean stopMovingDown();

  abstract boolean moveRight();

  abstract boolean stopMoving();

  abstract boolean stopMovingRight();

  abstract boolean stopMovingLeft();

  abstract void setImagePath(String imagePath);

  abstract ArrayList<Life> getLife();

  abstract void loseALife();

  abstract boolean hasLostALife();

  abstract void setLostALife(boolean lostALife);

  abstract boolean getIsMovingRight();

  abstract void setIsMovingRight(boolean isMovingRight);

  abstract boolean getIsMovingLeft();

  abstract void setIsMovingLeft(boolean isMovingLeft);

  abstract boolean getIsMovingDown();

  abstract void setIsMovingDown(boolean isMovingDown);

  abstract boolean getIsJumping();

  abstract void setIsJumping(boolean isJumping);
}
