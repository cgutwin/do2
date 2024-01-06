package ca.cgutwin.deckedout2.screens;

import ca.cgutwin.deckedout2.GameRunner;
import ca.cgutwin.deckedout2.physics.PhysicsSystem;
import ca.cgutwin.deckedout2.physics.collisions.CollisionSystem;
import ca.cgutwin.deckedout2.rendering.TileEntityRenderingSystem;
import ca.cgutwin.deckedout2.world.MapLoader;
import ca.cgutwin.deckedout2.world.MapRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class DungeonScreen implements Screen
{
  private final MapRenderer mapRenderer;
  GameRunner parent;
  MapLoader mapLoader;
  OrthographicCamera camera;

  public DungeonScreen(GameRunner parent) {
    this.parent      = parent;
    this.mapLoader   = new MapLoader(parent.engine(), "tiledmap/untitled.tmx");
    this.mapRenderer = new MapRenderer(mapLoader.map());

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    camera.update();

    parent.engine().addSystem(new TileEntityRenderingSystem(parent.sb()));
    parent.engine().addSystem(new PhysicsSystem(parent.world()));
    parent.engine().addSystem(new CollisionSystem(parent.world()));
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    parent.engine().update(delta);
    camera.update();

    mapRenderer.setView(camera);
    mapRenderer.render();
  }

  @Override
  public void resize(int width, int height) {

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
