package stickman.model;

public interface CharacterState {
  void change(Character character, String newState);

  void updateScreen(Character character);
}
