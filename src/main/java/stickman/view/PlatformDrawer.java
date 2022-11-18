package stickman.view;

public interface PlatformDrawer {
  // using factory method to create a different set of platforms. PlatformFactory method is in
  // charge of creating platforms
  void draw();
  // void move();
}
