package ca.cgutwin.deckedout.game.ecs.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraController {
  private final OrthographicCamera camera;

  private final Vector2 focalPoint;
  private final float viewportWidth;
  private final float viewportHeight;
  private final Viewport viewport;

  public CameraController(int viewportWidth, int viewportHeight) {
    this.viewportWidth  = viewportWidth;
    this.viewportHeight = viewportHeight;
    this.camera         = new OrthographicCamera();
    this.focalPoint     = new Vector2();
    viewport            = new ExtendViewport(800, 480, camera);
    configureCamera();
  }

  private void configureCamera() {
    camera.setToOrtho(false, viewportWidth, viewportHeight);
    camera.zoom = 0.3F;
    updateCameraPosition();
  }

  public void updateCameraPosition() {
    camera.position.set(focalPoint.x, focalPoint.y, 0);
    clampCamera(camera, 40*8, 40*8);
    camera.update();
  }

  public void clampCamera(OrthographicCamera camera, float mapPixelWidth, float mapPixelHeight) {
    float cameraHalfWidth = camera.viewportWidth*0.5f*camera.zoom;
    float cameraHalfHeight = camera.viewportHeight*0.5f*camera.zoom;

    // Clamping the camera position to the bounds of the map
    float minX = cameraHalfWidth;
    float maxX = mapPixelWidth-cameraHalfWidth;
    float minY = cameraHalfHeight;
    float maxY = mapPixelHeight-cameraHalfHeight;

    camera.position.x = MathUtils.clamp(camera.position.x, minX, maxX);
    camera.position.y = MathUtils.clamp(camera.position.y, minY, maxY);
  }

  public void setFocalPoint(float x, float y) {
    focalPoint.set(x, y);
  }

  public void update(float delta) {
    camera.update();
  }

  public OrthographicCamera getCamera() {
    return this.camera;
  }

  public Viewport getViewport() {
    return viewport;
  }
}

