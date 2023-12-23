package ca.cgutwin.deckedout.states;

import ca.cgutwin.deckedout.GameState;
import ca.cgutwin.deckedout.core.GameStateManager;
import ca.cgutwin.deckedout.screens.LevelTransitionScreen;
import ca.cgutwin.deckedout.screens.MainGameScreen;
import ca.cgutwin.deckedout.screens.PauseOverlay;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.StringJoiner;

public class PlayState extends GameState {
  private final Game game;
  private final PauseOverlay pauseOverlay;
  private boolean isPaused;
  private State currentState;


  public PlayState(Game game, GameStateManager gsm) {
    super(gsm);
    this.game = game;

    pauseOverlay = new PauseOverlay(gsm);
    isPaused     = false;

    currentState = State.MAIN_GAME;
    setPlayScreen(currentState);
  }

  public void setPlayScreen(State newState) {
    currentState = newState;

    switch (newState) {
      case MAIN_GAME:
        game.setScreen(new MainGameScreen(gsm));
        break;
      case LEVEL_TRANSITION:
        game.setScreen(new LevelTransitionScreen());
        break;
    }
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PlayState.class.getSimpleName() + "[", "]").add("currentState=" + currentState)
                                                                             .toString();
  }

  @Override
  public void update(float dt) {
    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
      isPaused = !isPaused;
      pauseOverlay.toggleVisibility();
    }
  }

  @Override
  public void render() {
    if (isPaused) {
      pauseOverlay.render();
    }

    game.getScreen().render(Gdx.graphics.getDeltaTime());
  }

  @Override
  public void dispose() {
  }


  public State currentState() {
    return currentState;
  }

  public enum State {
    MAIN_GAME, LEVEL_TRANSITION
    // Add other states as needed
  }
}
