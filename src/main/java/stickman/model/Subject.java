package stickman.model;

public interface Subject {
  void register(Observer observer);

  void unregister(Observer observer);

  void notifyObservres();

  String getUpdate(Observer observer);
}
