package ca.cgutwin.deckedout.game.core;

import ca.cgutwin.ECSManager;
import ca.cgutwin.components.DeckComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class DebugCardScreen implements Screen {
  ECSManager ecs;
  GameApplication game;

  public DebugCardScreen(GameApplication game, ECSManager ecs) {
    this.ecs  = ecs;
    this.game = game;
  }

  /**
   *
   */
  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    // Clear the screen
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    DeckComponent deck = ecs.getComponentManager()
                            .getComponent(ecs.getEntityManager().getPlayer(), DeckComponent.class);
    deck.render(game.getSb());
  }

  /**
   * @param i
   * @param i1
   */
  @Override
  public void resize(int i, int i1) {

  }

  /**
   *
   */
  @Override
  public void pause() {

  }

  /**
   *
   */
  @Override
  public void resume() {

  }

  /**
   *
   */
  @Override
  public void hide() {

  }

  /**
   *
   */
  @Override
  public void dispose() {

  }
}
