package ca.cgutwin.deckedout.core;

import ca.cgutwin.deckedout.GameState;

import java.util.Stack;

public class GameStateManager {
  private final Stack<GameState> states;

  public GameStateManager() {
    states = new Stack<>();
  }

  public void push(GameState state) {
    states.push(state);
  }

  public void pop() {
    states.pop().dispose();
  }

  public void set(GameState state) {
    states.pop().dispose();
    states.push(state);
  }

  public void update(float dt) {
    states.peek().update(dt);
  }

  public void render() {
    states.peek().render();
  }
}
