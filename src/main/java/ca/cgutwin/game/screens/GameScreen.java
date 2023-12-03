package ca.cgutwin.game.screens;

import ca.cgutwin.game.core.GameApplication;
import ca.cgutwin.game.ecs.managers.GameStateManager;
import ca.cgutwin.game.states.PlayState;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
  private final GameApplication game;
  private final GameStateManager stateManager;

  public GameScreen(GameApplication game) {
    this.game         = game;
    this.stateManager = new GameStateManager();
    stateManager.pushState(new PlayState(stateManager));
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    stateManager.update(delta);
    stateManager.render(game.getBatch());
  }

  @Override
  public void resize(int i, int i1) {

  }

  @Override
  public void pause() {
    System.out.println("pause");
  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }
}
