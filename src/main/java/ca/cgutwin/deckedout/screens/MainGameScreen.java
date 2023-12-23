package ca.cgutwin.deckedout.screens;

import ca.cgutwin.deckedout.core.GameStateManager;
import ca.cgutwin.deckedout.world.LevelManager;
import ca.cgutwin.deckedout.world.MapManager;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MainGameScreen implements Screen {
  private final LevelManager levelManager;
  private final MapManager mapManager;
  private final OrthographicCamera camera;
  private final OrthogonalTiledMapRenderer mapRenderer;

  public MainGameScreen(GameStateManager gsm) {
    this.mapManager   = new MapManager();
    this.levelManager = new LevelManager(mapManager);
    camera            = new OrthographicCamera();

    levelManager.loadLevel(LevelManager.LEVEL_ONE);

    camera.setToOrtho(false, 800, 480);
    camera.update();

    mapRenderer = new OrthogonalTiledMapRenderer(mapManager.getCurrentMap());
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float dt) {
    camera.update();
    mapRenderer.setView(camera);
    mapRenderer.render();
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
