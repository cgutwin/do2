package ca.cgutwin.deckedout.game.core;

import ca.cgutwin.ECSManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends Game {
  private SpriteBatch sb;
  private ECSManager ecs;

  @Override
  public void create() {
    this.sb = new SpriteBatch();
    this.ecs = new ECSManager();
    this.ecs.init();

    sb.begin();

    setScreen(new DebugCardScreen(this, ecs));
  }

  public SpriteBatch getSb() {
    return sb;
  }

  @Override
  public void dispose() {
    sb.dispose();
  }
}
