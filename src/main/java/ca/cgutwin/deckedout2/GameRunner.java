package ca.cgutwin.deckedout2;

import ca.cgutwin.deckedout2.screens.DungeonScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameRunner extends Game
{
  private SpriteBatch sb;
  private Engine engine;
  private World world;

  public World world() {
    return world;
  }

  public SpriteBatch sb() {
    return sb;
  }

  public Engine engine() {
    return engine;
  }

  @Override
  public void create() {
    this.sb     = new SpriteBatch();
    this.engine = new Engine();
    this.world  = new World(new Vector2(0, 0), true);

    this.setScreen(new DungeonScreen(this));
  }

  @Override
  public void dispose() {
    sb.dispose();
  }

  @Override
  public void render() {
    super.render();
    engine.update(Gdx.graphics.getDeltaTime());
  }
}
