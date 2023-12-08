package ca.cgutwin.deckedout.game.core;

import ca.cgutwin.deckedout.game.core.screens.PlayScreen;
import ca.cgutwin.deckedout.game.ecs.managers.GameStateManager;
import ca.cgutwin.deckedout.game.ecs.managers.MapManager;
import ca.cgutwin.deckedout.game.ecs.systems.EventSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends Game {
  private SpriteBatch sb;
  private EventSystem eventSystem;




  @Override
  public void create() {
    this.sb = new SpriteBatch();
    GameStateManager gameStateManager = new GameStateManager();
    MapManager mapManager = new MapManager();

    // Event System Setup
    eventSystem  = EventSystem.getInstance();


    setScreen(new PlayScreen(this, gameStateManager, mapManager, eventSystem));
  }

  @Override
  public void dispose() {
    sb.dispose();
  }

  public SpriteBatch getSpriteBatch() {
    return this.sb;
  }
}
