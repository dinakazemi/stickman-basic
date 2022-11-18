package stickman.model;
/** Creates a character with the size "normal" (height = 55 and width = 55) */
public class NormalCharacter implements CharacterSize {

  /**
   * Tells character the height of the normal character (in pixels)
   *
   * @return height of the normal character (55 pixels)
   */
  @Override
  public double getHeight() {
    return 55;
  }

  /**
   * Tells character the width of the normal character (in pixels)
   *
   * @return width of the large character (55 pixels)
   */
  @Override
  public double getWidth() {
    return 55;
  }
}
