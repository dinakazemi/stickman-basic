package stickman.view;
/**
 * Creates a KeyBoardInterruptHandler object used for handling user inputs. The only valid inputs
 * are the up, left, and right arrow
 */
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import stickman.model.GameEngine;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// for handling keyboard interactions with the game
class KeyboardInputHandler {
  private final GameEngine model;
  private boolean left = false;
  private boolean right = false;
  // private boolean up = false;
  private Set<KeyCode> pressedKeys = new HashSet<>();

  private Map<String, MediaPlayer> sounds = new HashMap<>();

  KeyboardInputHandler(GameEngine model) {
    this.model = model;

    URL mediaUrl = getClass().getResource("/jump.wav");
    String jumpURL = mediaUrl.toExternalForm();

    Media sound = new Media(jumpURL);
    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    sounds.put("jump", mediaPlayer);
  }

  /**
   * Called from game window to handle the situation where the user has pressed the up, left, or
   * right buttons
   *
   * @param keyEvent The keyEvent object generated when the key is released.
   */
  void handlePressed(KeyEvent keyEvent) {
    if (pressedKeys.contains(keyEvent.getCode())) {
      return;
    }
    pressedKeys.add(keyEvent.getCode());

    if (keyEvent.getCode().equals(KeyCode.UP)) {
      if (model.jump()) {
        // up = true;
        MediaPlayer jumpPlayer = sounds.get("jump");
        jumpPlayer.stop();
        jumpPlayer.play();
      }
    }

    if (keyEvent.getCode().equals(KeyCode.LEFT)) {
      left = true;
    } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
      right = true;
    } else {
      return;
    }

    if (left) {

      if (right) {
        model.stopMoving();
      } else {
        model.moveLeft();
      }

    } else {
      model.moveRight();
    }
  }

  /**
   * Called from game window to handle the situation where the user has released the up, left, or
   * right buttons
   *
   * @param keyEvent The keyEvent object generated when the key is pressed.
   */
  void handleReleased(KeyEvent keyEvent) {
    pressedKeys.remove(keyEvent.getCode());

    if (keyEvent.getCode().equals(KeyCode.LEFT)) {
      left = false;
    } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
      right = false;
    } else {
      return;
    }

    if (!(right || left)) {
      model.stopMoving();
    } else if (right) {
      model.moveRight();
    } else {
      model.moveLeft();
    }
  }
}
