package ca.cgutwin.game.screens;

import ca.cgutwin.game.core.GameApplication;
import ca.cgutwin.game.ecs.managers.GameStateManager2;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
  private final GameApplication game;
  private final GameStateManager2 stateManager;


  public GameScreen(GameApplication game) {
    this.game         = game;
    stateManager = new GameStateManager2();
    stateManager.changeLevel("Level1");
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    stateManager.update(delta);
    stateManager.render();
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
    stateManager.dispose();
  }
}
