package ca.cgutwin.game.core.camera.movement;

import ca.cgutwin.game.core.camera.CameraController;

public class MoveCameraUpCommand implements ICommand {
  int speed = 1;
  private final CameraController cameraController;

  public MoveCameraUpCommand(CameraController cameraController) {
    this.cameraController = cameraController;
  }

  @Override
  public void execute() {
    // Move the camera upwards
    cameraController.moveUp(speed);
  }
}