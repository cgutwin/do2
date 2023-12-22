package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.core.WorldManager;
import ca.cgutwin.deckedout.core.screens.WorldScreen;
import ca.cgutwin.deckedout.ecs.entities.Player;
import ca.cgutwin.deckedout.factories.Box2DBodyFactory;
import ca.cgutwin.deckedout.factories.Box2DBodyFactoryImpl;
import ca.cgutwin.deckedout.world.LevelManager;
import ca.cgutwin.deckedout.world.MapManager;
import ca.cgutwin.deckedout.world.MapRenderingSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DeckedOut extends Game {
  private final WorldManager worldManager;
  private final LevelManager levelManager;
  private final MapManager mapManager;
  private final Box2DBodyFactory bodyFactory;
  private final MapRenderingSystem mapRenderer;
  private final Engine engine;
  private Player player;
  private SpriteBatch spriteBatch;

  public DeckedOut() {
    engine       = new PooledEngine();
    worldManager = new WorldManager();
    mapManager   = new MapManager();
    levelManager = new LevelManager(mapManager);
    mapRenderer  = new MapRenderingSystem(mapManager, new OrthographicCamera());
    bodyFactory  = new Box2DBodyFactoryImpl(worldManager.world());
  }

  public Engine engine() {
    return engine;
  }

  public Player player() {
    return player;
  }

  @Override
  public void create() {
    spriteBatch = new SpriteBatch();
    player      = new Player(bodyFactory, "img_1.png", 2, 2);
    levelManager.loadLevel(LevelManager.LEVEL_ONE);

    setScreen(new WorldScreen(this));
  }

  @Override
  public void dispose() {
    super.dispose();
    spriteBatch.dispose();
  }

  @Override
  public void render() {
    super.render();
  }

  public SpriteBatch spriteBatch() {
    return spriteBatch;
  }

  public WorldManager worldManager() {
    return worldManager;
  }
}
