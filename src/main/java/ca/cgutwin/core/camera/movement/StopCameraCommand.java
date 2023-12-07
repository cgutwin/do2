package ca.cgutwin.core.camera.movement;

import ca.cgutwin.ecs.controllers.CameraController;

public class StopCameraCommand implements ICommand {
  private final CameraController cameraController;

  public StopCameraCommand(CameraController cameraController) {
    this.cameraController = cameraController;
  }

  @Override
  public void execute() {
    // Move the camera upwards
    cameraController.stop();
  }
}