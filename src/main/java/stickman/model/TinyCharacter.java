package stickman.model;
/** Creates a character with the size "tiny" (height = 40 and width = 40) */
public class TinyCharacter implements CharacterSize {

  /**
   * Tells character the height of the tiny character (in pixels)
   *
   * @return height of the tiny character (40 pixels)
   */
  @Override
  public double getHeight() {
    return 40;
  }

  /**
   * Tells character the width of the normal character (in pixels)
   *
   * @return width of the tiny character (40 pixels)
   */
  @Override
  public double getWidth() {
    return 40;
  }
}
