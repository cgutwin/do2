package ca.cgutwin.deckedout.core;

import ca.cgutwin.deckedout.MainGame;
import ca.cgutwin.deckedout.PlayScreen;
import ca.cgutwin.deckedout.world.LevelManager;

public class PlayState {
  private final MainGame game;
  private final LevelManager levelManager;
  private State currentState;

  public PlayState(MainGame game) {
    this.game         = game;
    this.levelManager = new LevelManager(null);
    currentState      = State.MAIN_GAME;
    setPlayScreen(currentState);
  }

  public void setPlayScreen(State newState) {
    currentState = newState;
    switch (newState) {
      case MAIN_GAME:
        game.setScreen(new PlayScreen(this.game));
        break;
      case PAUSE_MENU:
        game.setScreen(new PlayScreen(this.game));
        break;
      case LEVEL_TRANSITION:
        game.setScreen(new PlayScreen(this.game));
        break;
      // Handle other states as needed
    }
  }

  public enum State {
    MAIN_GAME, PAUSE_MENU, LEVEL_TRANSITION
    // Add other states as needed
  }
}
