package ca.cgutwin.core.camera.movement;

import ca.cgutwin.ecs.controllers.CameraController;

public class MoveCameraCommand implements ICommand {
  private final CameraController cameraController;
  private final EDirections direction;

  public MoveCameraCommand(CameraController cameraController, EDirections direction) {
    this.cameraController = cameraController;
    this.direction = direction;
  }

  @Override
  public void execute() {
    // Move the camera upwards
    cameraController.setMovementDirection(direction);
  }
}