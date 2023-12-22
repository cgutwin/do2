package ca.cgutwin.deckedout.card2.cardSelectionScreen;

import ca.cgutwin.deckedout.DeckSystem;
import ca.cgutwin.deckedout.card2.CardManager;
import ca.cgutwin.deckedout.core.GameApplication;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CardSelectionScreen implements Screen {
  private final CardManager cardManager;
  private final DeckSystem deckSystem;
  private final GameApplication game;
  private final Stage stage;
  private final CardGrid cardGrid;
  private final Skin skin;


  public CardSelectionScreen(GameApplication game) {
    this.cardManager = game.cardManager();
    this.deckSystem  = game.deckSystem();
    this.game        = game;

    stage    = new Stage(new ScreenViewport());
    cardGrid = new CardGrid(cardManager, deckSystem);
    skin     = new Skin(Gdx.files.internal("skins/menu1/menu1.json"));

    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void show() {
    Table table = new Table();
    table.setFillParent(true);
    table.setDebug(true);

    table.add().growX();
    table.add(cardGrid.scrollPane()).growX().growY().maxSize(600.0f);
    table.add().growX();
    table.row();

    ImageTextButton saveDeck = new ImageTextButton("save deck", skin);
    ImageTextButton loadDeck = new ImageTextButton("load deck", skin);
    ImageTextButton start = new ImageTextButton("start", skin);

    table.add(saveDeck);
    table.add(loadDeck);
    table.add(start);

    saveDeck.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        deckSystem.saveSelectedCards("deck1");
        System.out.println("saved");
      }
    });

    loadDeck.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        deckSystem.loadSelectedCards("deck1");
        cardGrid.update();
        System.out.println("loaded");
      }
    });

    start.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        game.startGame();
        dispose();
      }
    });

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
