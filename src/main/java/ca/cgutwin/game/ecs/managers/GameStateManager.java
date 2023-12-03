package ca.cgutwin.game.ecs.managers;

import ca.cgutwin.game.states.GameState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
  private final Stack<GameState> gameStates;

  public GameStateManager() {
    gameStates = new Stack<>();
  }

  public void pushState(GameState state) {
    gameStates.push(state);
  }

  public void popState() {
    gameStates.pop().dispose();
  }

  public void setState(GameState state) {
    popState();
    pushState(state);
  }

  public void update(float dt) {
    gameStates.peek().update(dt);
  }

  public void render(SpriteBatch sb) {
    gameStates.peek().render(sb);
  }
}
