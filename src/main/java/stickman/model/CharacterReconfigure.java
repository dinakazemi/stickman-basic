package stickman.model;
/**
 * The state that the character has when he has to start the game from the last configured position
 * - eg when hitting an enemy not from above
 */
public class CharacterReconfigure implements CharacterState {

  /**
   * Called when the characterImpl must change its states according to events in LevelImpl - 3 possible
   * states to change to: CharacterWon, CharacterContinue, CharacterLost
   *
   * @param character The characterImpl that its state must be changed
   * @param newState The new state that the characterImpl must change to as a String
   */
  @Override
  public void change(Character character, String newState) {

    // if the characterImpl can continue the game as expected
    if (newState.equalsIgnoreCase("Continue")) {
      CharacterState state = new CharacterContinue();
      character.setState(state);
    }
    // if the characterImpl loses the game
    else if (newState.equalsIgnoreCase("Lost")) {
      CharacterState state = new CharacterLost();
      character.setState(state);
    }
    // if the characterImpl wins the game
    else if (newState.equalsIgnoreCase("Won")) {
      CharacterState state = new CharacterWon();
      character.setState(state);
    }
  }

  /**
   * updates the screen according to the state - places the characterImpl in the lats configured
   * position
   *
   * @param character The characterImpl in the game that the screen must be updated according to their
   *     state
   */
  @Override
  public void updateScreen(Character character) {
    character.setXPos(character.getInitialX());
    for (int i = 0; i < character.getLife().size(); i++) {
      character.getLife().get(i).setXPos(character.getInitialX() * (i + 1));
    }
  }
}
