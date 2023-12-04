package ca.cgutwin.game.core.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
  private final OrthographicCamera camera;

  public CameraController(OrthographicCamera camera) {
    this.camera = camera;
    camera.setToOrtho(false, 800, 480);
  }

  public void moveUp(float speed) {
    // Adjust the camera's y-coordinate
    camera.translate(0, speed);
    camera.update();
  }

  public void stop() {
    // Logic to stop camera movement
    // This could be as simple as setting the movement speed to 0,
    // or more complex if you have a smooth stopping mechanism
    return;
  }
}
