package stickman.model;

public interface Observer {
  void setSubject(Subject subject);

  void update();
}
