package ca.cgutwin.deckedout.game.core.screens;

import ca.cgutwin.deckedout.game.core.GameApplication;
import ca.cgutwin.deckedout.game.ecs.managers.GameStateManager;
import ca.cgutwin.deckedout.game.ecs.managers.MapManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {

  private final GameApplication game;
  private final OrthographicCamera camera;

  public MenuScreen(GameApplication game, GameStateManager gameStateManager, MapManager mapManager) {
    this.game = game;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0.2f, 1);

    camera.update();

    if (Gdx.input.isTouched()) {
      System.out.println("dispose");
      dispose();
    }
  }

  @Override
  public void resize(int i, int i1) {

  }

  @Override
  public void pause() {

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
