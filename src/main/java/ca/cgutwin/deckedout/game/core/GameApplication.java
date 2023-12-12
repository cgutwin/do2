package ca.cgutwin.deckedout.game.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends Game {
  private SpriteBatch sb;

  public void create() {
    this.sb = new SpriteBatch();
  }

  @Override
  public void dispose() {
    sb.dispose();
  }
}
