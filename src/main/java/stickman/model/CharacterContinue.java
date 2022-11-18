package stickman.model;

/** The state that the character has when he should continue the game as usual */
public class CharacterContinue implements CharacterState {

  /**
   * Called when the character must change its states according to events in LevelImpl - 3 possible
   * states to change to: CharacterReconfigure, CharacterWon, CharacterLost
   *
   * @param character The character that its state must be changed
   * @param newState The new state that the character must change to as a String
   */
  @Override
  public void change(Character character, String newState) {

    if (newState.equalsIgnoreCase("Reconfigure")) {
      CharacterState state = new CharacterReconfigure();
      character.setState(state);
    } else if (newState.equalsIgnoreCase("Lost")) {
      CharacterState state = new CharacterLost();
      character.setState(state);
    } else if (newState.equalsIgnoreCase("Won")) {
      CharacterState state = new CharacterWon();
      character.setState(state);
    }
  }

  /**
   * updates the screen according to the state - as the character is expected to continue the game
   * as usual in this state, this method does nothing
   *
   * @param character The character in the game that the screen must be updated according to them
   */
  @Override
  public void updateScreen(Character character) {}
}
