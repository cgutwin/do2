package ca.cgutwin.game.states;

import ca.cgutwin.game.ecs.managers.GameStateManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {
  protected final GameStateManager gameStateManager;

  public GameState(GameStateManager gsm) {
    this.gameStateManager = gsm;
  }

  public abstract void handleInput();
  public abstract void update(float delta);
  public abstract void render(SpriteBatch sb);
  public abstract void dispose();

  // Optionally, methods for entering and exiting the state
  public abstract void enter();
  public abstract void exit();
}