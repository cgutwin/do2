package ca.cgutwin.deckedout2.screens;

import ca.cgutwin.deckedout2.GameRunner;
import ca.cgutwin.deckedout2.physics.PhysicsSystem;
import ca.cgutwin.deckedout2.physics.collisions.CollisionSystem;
import ca.cgutwin.deckedout2.player.PlayerCreationSystem;
import ca.cgutwin.deckedout2.rendering.RenderingSystem;
import ca.cgutwin.deckedout2.util.DebugSystem;
import ca.cgutwin.deckedout2.world.MapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * The DungeonScreen class represents the screen for the dungeon gameplay.
 */
public class DungeonScreen implements Screen
{
  private final Box2DDebugRenderer worldRenderer;
  GameRunner parent;
  MapLoader mapLoader;
  OrthographicCamera camera;
  Viewport viewport;

  /**
   * Constructs a DungeonScreen instance.
   *
   * @param parent The GameRunner instance that manages the game.
   */
  public DungeonScreen(GameRunner parent) {
    this.parent    = parent;
    this.camera    = new OrthographicCamera();
    this.mapLoader = new MapLoader(parent.engine(), camera, "tiledmap/untitled.tmx");

    worldRenderer = new Box2DDebugRenderer();

    viewport = new ExtendViewport(10*32, 10*32, camera);
    camera.update();

    // Add various systems to the Ashley engine
    parent.engine().addSystem(new PhysicsSystem(parent.world()));
    parent.engine().addSystem(new RenderingSystem(parent.sb()));
    parent.engine().addSystem(new CollisionSystem(parent.world()));
    parent.engine().addSystem(new PlayerCreationSystem(parent.world()));

    DebugSystem.getInstance().sout("engines added: " + parent.engine().getSystems());
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    viewport.apply(true);
    parent.sb().setProjectionMatrix(camera.combined);

    worldRenderer.render(parent.world(), camera.combined);
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
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
    // Dispose of any resources here if needed.
  }
}
