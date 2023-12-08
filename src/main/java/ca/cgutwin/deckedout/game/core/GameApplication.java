package ca.cgutwin.deckedout.game.core;

import ca.cgutwin.deckedout.game.core.screens.PlayScreen;
import ca.cgutwin.deckedout.game.ecs.managers.GameStateManager;
import ca.cgutwin.deckedout.game.ecs.managers.MapManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends Game {
  SpriteBatch sb;

  @Override
  public void create() {
    this.sb = new SpriteBatch();
    GameStateManager gameStateManager = new GameStateManager();
    MapManager mapManager = new MapManager();

    setScreen(new PlayScreen(this, gameStateManager, mapManager));
  }

  @Override
  public void dispose() {
    sb.dispose();
  }

  public SpriteBatch getSpriteBatch() {
    return this.sb;
  }
}
