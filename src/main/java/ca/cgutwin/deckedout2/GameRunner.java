package ca.cgutwin.deckedout2;

import ca.cgutwin.deckedout2.screens.DungeonScreen;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRunner extends Game
{
  private SpriteBatch sb;
  private Engine engine;

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
