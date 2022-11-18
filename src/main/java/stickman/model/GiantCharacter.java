package stickman.model;

/** Creates a character with the size "giant" (height = 80 and width = 80) */
public class GiantCharacter implements CharacterSize {

  /**
   * Tells character the height of the giant character (in pixels)
   * @return height of the giant character (80 pixels)
   */
  @Override
  public double getHeight() {
    return 80;
  }
  /**
   * Tells character the width of the giant character (in pixels)
   * @return width of the giant character (80 pixels)
   */
  @Override
  public double getWidth() {
    return 80;
  }
}
