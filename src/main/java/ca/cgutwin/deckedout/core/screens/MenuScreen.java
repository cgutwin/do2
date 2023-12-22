package ca.cgutwin.deckedout.core.screens;

import ca.cgutwin.deckedout.ComponentManager;
import ca.cgutwin.deckedout.card2.cardSelectionScreen.CardSelectionScreen;
import ca.cgutwin.deckedout.core.GameApplication;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
  private final GameApplication game;
  private final SpriteBatch sb;
  private final ComponentManager componentManager;
  private final Stage stage;
  private Skin skin;

  public MenuScreen(GameApplication game, ComponentManager componentManager) {
    this.game = game;

    sb                    = game.getSb();
    this.componentManager = componentManager;
    stage                 = new Stage(new ScreenViewport());

    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void show() {
    Table table = new Table();
    table.setFillParent(true);
    table.setDebug(true);
    stage.addActor(table);

    skin = new Skin(Gdx.files.internal("skins/menu1/menu1.json"));

    table.add().growX();

    ImageTextButton startButton = new ImageTextButton("START GAME", skin);
    table.add(startButton).growX().maxWidth(300.0f);

    table.add().growX();

    table.row();
    table.add().growX();

    ImageTextButton worldButton = new ImageTextButton("world", skin);
    table.add(worldButton).growX().maxWidth(300.0f);

    table.add().growX();
    table.row();

    table.add().growX();
    ImageTextButton quitButton = new ImageTextButton("QUIT", skin);
    table.add(quitButton).growX().maxWidth(300.0f);
    table.add().growX();

    stage.addActor(table);

    startButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("start!");
        game.startGame();
        dispose();
      }
    });

    worldButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        System.out.println("select!");
        game.setScreen(new CardSelectionScreen(game));
        dispose();
      }
    });

    quitButton.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        Gdx.app.exit();
      }
    });
  }

  @Override
  public void render(float dt) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    sb.begin();

    stage.act(dt);
    stage.draw();

    sb.end();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
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
    skin.dispose();
  }
}
