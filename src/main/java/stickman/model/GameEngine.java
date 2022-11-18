package stickman.model;

import stickman.view.GameWindow;

public interface GameEngine {
  Level getCurrentLevel();

  void startLevel();

  // Hero inputs - boolean for success (possibly for sound feedback)
  boolean jump();

  boolean moveLeft();

  boolean moveRight();

  boolean stopMoving();

  void tick(GameWindow gameWindow);
}
