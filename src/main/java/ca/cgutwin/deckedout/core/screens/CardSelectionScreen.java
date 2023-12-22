package ca.cgutwin.deckedout.core.screens;

import ca.cgutwin.deckedout.ComponentManager;
import ca.cgutwin.deckedout.core.GameApplication;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CardSelectionScreen implements Screen {
  private final GameApplication game;
  private final SpriteBatch sb;
  private final ComponentManager componentManager;
  private final Stage stage;
  private final CardGrid cardGrid;

  public CardSelectionScreen(GameApplication game, ComponentManager componentManager) {
    this.game = game;

    sb                    = game.getSb();
    this.componentManager = componentManager;
    stage                 = new Stage(new ScreenViewport());
    cardGrid              = new CardGrid(game.deckComponent().cardsInDeck());

    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void show() {
    Table table = new Table();
    table.setFillParent(true);
    table.setDebug(true);

    table.add().growX();
    table.add(cardGrid.getScrollPane()).growX().growY().maxSize(600.0f);
    cardGrid.updateCardLayout(stage.getWidth());
    table.add().growX();

    stage.addActor(table);
  }

  @Override
  public void render(float dt) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(dt);
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
    cardGrid.updateCardLayout(Math.min(width, 600));
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
    stage.dispose();
  }
}
