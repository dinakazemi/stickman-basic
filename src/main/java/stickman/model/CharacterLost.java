package stickman.model;
/** The state that the character has when he has lost the game */
public class CharacterLost implements CharacterState {

  /**
   * Called when the characterImpl must change its states according to events in LevelImpl - 2 possible
   * states to change to: CharacterReconfigure, CharacterContinue
   *
   * @param character The characterImpl that its state must be changed
   * @param newState The new state that the characterImpl must change to as a String
   */
  @Override
  public void change(Character character, String newState) {

    if (newState.equalsIgnoreCase("Reconfigure")) {
      CharacterState state = new CharacterReconfigure();
      character.setState(state);
    } else if (newState.equalsIgnoreCase("Continue")) {
      CharacterState state = new CharacterContinue();
      character.setState(state);
    }
  }
  /**
   * updates the screen according to the state - prints out the message "You have lost! :("
   *
   * @param character The characterImpl in the game that the screen must be updated according to their
   *     state
   */
  @Override
  public void updateScreen(Character character) {
    System.out.println("You have lost! :(");
  }
}
