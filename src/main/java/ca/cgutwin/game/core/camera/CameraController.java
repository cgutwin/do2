package ca.cgutwin.game.core.camera;

import ca.cgutwin.game.core.camera.movement.EDirections;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraController {
  private final OrthographicCamera camera;
  private final Vector2 movementDirection = new Vector2(0, 0);
  private final float moveSpeed = 2.0f;  // Speed of camera movement

  public CameraController(OrthographicCamera camera) {
    this.camera = camera;
    camera.setToOrtho(false, 800, 480);
    camera.zoom = 0.75F;
  }

  public void setMovementDirection(EDirections direction) {
    switch (direction) {
      case UP:
        movementDirection.set(0, 1);
        break;
      case DOWN:
        movementDirection.set(0, -1);
        break;
      case LEFT:
        movementDirection.set(-1, 0);
        break;
      case RIGHT:
        movementDirection.set(1, 0);
        break;
    }
  }

  public void update() {
    // Update the camera's position based on the movement direction
    camera.translate(movementDirection.x*moveSpeed, movementDirection.y*moveSpeed);
    camera.update();
  }

  public void stop() {
    // Logic to stop camera movement
    // This could be as simple as setting the movement speed to 0,
    // or more complex if you have a smooth stopping mechanism
    movementDirection.set(0, 0);
  }
}
