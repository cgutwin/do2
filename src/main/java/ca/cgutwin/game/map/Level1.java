package ca.cgutwin.game.map;

import ca.cgutwin.game.core.camera.CameraController;
import ca.cgutwin.game.core.camera.movement.EDirections;
import ca.cgutwin.game.core.camera.movement.MoveCameraCommand;
import ca.cgutwin.game.core.camera.movement.StopCameraCommand;
import ca.cgutwin.game.io.InputManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Level1 implements Level {
  private final OrthographicCamera camera;
  private final CameraController cameraController;
  private MapManager mapManager;

  public Level1() {
    this.camera      = new OrthographicCamera();
    cameraController = new CameraController(camera);
    mapManager = new MapManager(camera);
    InputManager inputManager = InputManager.getInstance();

    inputManager.bindKeyDown(Input.Keys.W, new MoveCameraCommand(cameraController, EDirections.UP));
    inputManager.bindKeyUp(Input.Keys.W, new StopCameraCommand(cameraController));
    inputManager.bindKeyDown(Input.Keys.S, new MoveCameraCommand(cameraController, EDirections.DOWN));
    inputManager.bindKeyUp(Input.Keys.S, new StopCameraCommand(cameraController));
    inputManager.bindKeyDown(Input.Keys.A, new MoveCameraCommand(cameraController, EDirections.LEFT));
    inputManager.bindKeyUp(Input.Keys.A, new StopCameraCommand(cameraController));
    inputManager.bindKeyDown(Input.Keys.D, new MoveCameraCommand(cameraController, EDirections.RIGHT));
    inputManager.bindKeyUp(Input.Keys.D, new StopCameraCommand(cameraController));
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(InputManager.getInstance());
  }

  @Override
  public void load() {
    mapManager.loadMap("./map.tmx");
  }

  @Override
  public void update(float delta) {

  }

  @Override
  public void render() {
    cameraController.update();
    this.render();
  }

  @Override
  public void dispose() {

  }

}
