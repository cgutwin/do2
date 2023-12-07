package ca.cgutwin.screens;

import ca.cgutwin.ecs.controllers.CameraController;
import ca.cgutwin.ecs.managers.MapManager;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {
  private final CameraController cameraController;
  private final MapManager mapManager;

  public GameScreen() {
    this.cameraController = new CameraController(new OrthographicCamera());
    this.mapManager       = new MapManager();

    mapManager.loadMap("./map.tmx");
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float v) {
    cameraController.update(v);
    mapManager.render(cameraController.getCamera());
  }

  @Override
  public void resize(int i, int i1) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    mapManager.dispose();
  }
}
