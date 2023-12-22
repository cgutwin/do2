package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.core.GameStateManager;

public abstract class GameState {
  protected GameStateManager gsm;

  public GameState(GameStateManager gsm) {
    this.gsm = gsm;
  }

  public abstract void update(float dt);

  public abstract void render();

  public abstract void dispose();
}
