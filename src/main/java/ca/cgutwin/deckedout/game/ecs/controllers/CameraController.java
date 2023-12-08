package ca.cgutwin.deckedout.game.ecs.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraController {
  private final OrthographicCamera camera;

  private final Vector2 focalPoint;
  private final float viewportWidth;
  private final float viewportHeight;

  public CameraController(int viewportWidth, int viewportHeight) {
    this.viewportWidth  = viewportWidth;
    this.viewportHeight = viewportHeight;
    this.camera         = new OrthographicCamera();
    this.focalPoint     = new Vector2();
    configureCamera();
  }

  private void configureCamera() {
    camera.setToOrtho(false, viewportWidth, viewportHeight);
    camera.zoom = 0.3F;
    updateCameraPosition();
  }

  public void setFocalPoint(float x, float y) {
    focalPoint.set(x, y);
  }

  public void updateCameraPosition() {
    camera.position.set(focalPoint.x, focalPoint.y, 0);
    camera.update();
  }

  public void update(float delta) {
    camera.update();
  }

  public OrthographicCamera getCamera() {
    return this.camera;
  }
}

