package ca.cgutwin.deckedout.core;

import ca.cgutwin.deckedout.ComponentManager;
import ca.cgutwin.deckedout.DeckSystem;
import ca.cgutwin.deckedout.events.EventDispatcher;
import ca.cgutwin.deckedout.physics.CollisionObjectCreator;
import ca.cgutwin.deckedout.systems.frostEmber.FrostEmberSystem;
import ca.cgutwin.deckedout.systems.treasure.TreasureSystem;
import ca.cgutwin.deckedout.ui.systems.CardsDrawnDisplay;
import ca.cgutwin.deckedout.ui.systems.treasure.MainEventsBar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
  private final GameApplication game;
  private final SpriteBatch sb;
  private final EventDispatcher eventDispatcher;
  private final TreasureSystem treasureSystem;
  private final FrostEmberSystem frostEmberSystem;
  private final DeckSystem deckSystem;
  //  private final MapRenderingSystem mapRenderingSystem;
  private final CameraController cameraController;
  private final CollisionObjectCreator collisionObjectCreator;
  TextureAtlas coinAtlas = new TextureAtlas("coins.atlas");
  TextureRegion frostEmber = new TextureRegion(new Texture("frostEmber.png"));
  private MainEventsBar treasureBar;
  private MainEventsBar frostEmberBar;
  private CardsDrawnDisplay cardDisplay;
  private TiledMap tiledMap;
  private final WorldManager worldManager;
  private final TiledMapManager tiledMapManager;
  private OrthogonalTiledMapRenderer mapRenderer;

  public GameScreen(
          GameApplication game,
          ComponentManager componentManager,
          EventDispatcher eventDispatcher,
          TreasureSystem treasureSystem,
          FrostEmberSystem frostEmberSystem,
          DeckSystem deckSystem
  ) {
    this.game             = game;
    this.sb               = game.getSb();
    this.eventDispatcher  = eventDispatcher;
    this.treasureSystem   = treasureSystem;
    this.frostEmberSystem = frostEmberSystem;
    this.deckSystem       = deckSystem;
    this.cameraController = new CameraController(800, 480);
    //    this.mapRenderingSystem = new MapRenderingSystem(game.mapManager(), cameraController.getCamera());
    worldManager           = new WorldManager();
    tiledMapManager        = new TiledMapManager("crypt.tmx");
    collisionObjectCreator = new CollisionObjectCreator(worldManager.world(), tiledMapManager.tiledMap(), 1f/16f);
  }

  /**
   *
   */
  @Override
  public void show() {
    this.treasureBar   = new MainEventsBar(eventDispatcher, coinAtlas.findRegion("coin1"), 0, 50);
    this.frostEmberBar = new MainEventsBar(eventDispatcher, frostEmber, 0, 0);
    this.cardDisplay   = new CardsDrawnDisplay(deckSystem);
    treasureBar.registerListeners("TreasureSpawned", "MaxTreasureReachedEvent", "TreasureDecrease");
    frostEmberBar.registerListeners("FrostEmberSpawned", "MaxFrostEmbersReached", "FrostEmbersDecrease");
    collisionObjectCreator.createCollisionObjects();
  }

  /**
   * @param dt time delta
   */
  @Override
  public void render(float dt) {
    ScreenUtils.clear(0, 0, 0.2f, 1);

    // Set the SpriteBatch's projection matrix to the camera's combined matrix
    //    sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

    update(dt);
    worldManager.dispose();
    tiledMapManager.dispose();
    game.engine().update(dt);

    sb.begin();
    treasureBar.render(sb);
    frostEmberBar.render(sb);
    cardDisplay.render(sb);
    sb.end();
  }

  /**
   * @param i
   * @param i1
   */
  @Override
  public void resize(int i, int i1) {
    cameraController.getCamera().setToOrtho(false, i, i1);
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

  private void update(float dt) {
    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      treasureSystem.incrementValueIfAble();
      frostEmberSystem.incrementValueIfAble();

      if (treasureSystem.value() > treasureSystem.maxValue()) {
        treasureSystem.dispatchMaxValueEvent();
      }
      else { treasureSystem.dispatchTreasureSpawnedEvent(); }

      if (frostEmberSystem.value() > frostEmberSystem.maxValue()) {
        frostEmberSystem.dispatchMaxValueEvent();
      }
      else { frostEmberSystem.dispatchSpawnFrostEmberEvent(); }
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
      treasureSystem.reduceValue();
      frostEmberSystem.reduceValue();
    }
  }
}
