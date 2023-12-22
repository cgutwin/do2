package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.world.LevelManager;
import ca.cgutwin.deckedout.world.MapManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class PlayScreen implements Screen {
  private final LevelManager levelManager;
  private final MapManager mapManager;
  private final OrthographicCamera camera;
  private final OrthogonalTiledMapRenderer mapRenderer;

  public PlayScreen(MainGame game) {
    this.mapManager   = new MapManager();
    this.levelManager = new LevelManager(mapManager);

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    camera.update();

    // Load the initial level
    levelManager.loadLevel(LevelManager.LEVEL_ONE); // Or any other starting level
    mapRenderer = new OrthogonalTiledMapRenderer(mapManager.getCurrentMap());
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mapRenderer.setView(camera);
    mapRenderer.render();

    // Add your game logic update and rendering here
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

  }
}
