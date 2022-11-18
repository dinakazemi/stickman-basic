package stickman.model;
/** Creates a character with the size "large" (height = 70 and width = 70) */
public class LargeCharacter implements CharacterSize {

  /**
   * Tells character the height of the large character (in pixels)
   *
   * @return height of the large character (70 pixels)
   */
  @Override
  public double getHeight() {
    return 70;
  }

  /**
   * Tells character the width of the large character (in pixels)
   *
   * @return width of the large character (70 pixels)
   */
  @Override
  public double getWidth() {
    return 70;
  }
}
