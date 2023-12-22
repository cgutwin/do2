package ca.cgutwin.deckedout;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
  private final OrthographicCamera camera;

  public CameraController(float viewportWidth, float viewportHeight) {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, viewportWidth, viewportHeight);
  }

  public void update(float deltaTime) {
    camera.update();
  }

  public OrthographicCamera camera() {
    return camera;
  }
  // Additional methods to manipulate the camera, like zoom or pan
}
