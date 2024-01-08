package ca.cgutwin.deckedout2;

import ca.cgutwin.deckedout2.input.GameInputMultiplexer;
import ca.cgutwin.deckedout2.input.processors.DebugInputProcessor;
import ca.cgutwin.deckedout2.input.processors.PlayerInputProcessor;
import ca.cgutwin.deckedout2.screens.DungeonScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameRunner extends Game
{
  GameInputMultiplexer inputMultiplexer;
  InputProcessor debugInputProcessor;
  PlayerInputProcessor playerInputProcessor;
  private SpriteBatch batch;
  private Engine engine;
  private World world;

  public World world() {
    return world;
  }

  public SpriteBatch sb() {
    return batch;
  }

  public Engine engine() {
    return engine;
  }

  @Override
  public void create() {
    this.batch  = new SpriteBatch();
    this.engine = new Engine();
    this.world  = new World(new Vector2(0, 0), true);

    setupInputMultiplexer();

    this.setScreen(new DungeonScreen(this));
  }

  private void setupInputMultiplexer() {
    inputMultiplexer = new GameInputMultiplexer();

    debugInputProcessor  = new DebugInputProcessor(Input.Keys.F1);
    playerInputProcessor = new PlayerInputProcessor(engine);

    inputMultiplexer.addProcessor(debugInputProcessor);
    inputMultiplexer.addProcessor(playerInputProcessor);

    Gdx.input.setInputProcessor(inputMultiplexer);
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  @Override
  public void render() {
    super.render();
    float delta = Gdx.graphics.getDeltaTime();

    playerInputProcessor.update(delta);
    engine.update(delta);
  }
}
