package stickman.model;

/** Builds characters with different size according to the size it has been given */
public class CharacterFactory {

  /**
   * Makes and returns a new character according to the size given to it as a parameter - the
   * parameter is either tiny, normal, large, or giant
   *
   * @param size The size of the character as a string
   * @return The newly built character, or null if the size is not valid
   */
  public static CharacterSize make(String size) {
    CharacterSize characterSize;

    if (size.equalsIgnoreCase("tiny")) {
      characterSize = new TinyCharacter();
      return characterSize;
    } else if (size.equalsIgnoreCase("normal")) {
      characterSize = new NormalCharacter();
      return characterSize;
    } else if (size.equalsIgnoreCase("large")) {
      characterSize = new LargeCharacter();
      return characterSize;
    } else if (size.equalsIgnoreCase("giant")) {
      characterSize = new GiantCharacter();
      return characterSize;
    }

    return null;
  }
}
