package ca.cgutwin.deckedout.core;

import ca.cgutwin.deckedout.ComponentManager;
import ca.cgutwin.deckedout.DeckComponent;
import ca.cgutwin.deckedout.DeckSystem;
import ca.cgutwin.deckedout.Player;
import ca.cgutwin.deckedout.card2.CardManager;
import ca.cgutwin.deckedout.card2.NormalCard;
import ca.cgutwin.deckedout.core.screens.MenuScreen;
import ca.cgutwin.deckedout.ecs.systems.MovementSystem;
import ca.cgutwin.deckedout.events.EventDispatcher;
import ca.cgutwin.deckedout.systems.frostEmber.FrostEmberSystem;
import ca.cgutwin.deckedout.systems.treasure.TreasureSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends Game {
  public Screen gameScreen;
  EventDispatcher eventDispatcher;
  ComponentManager componentManager;
  TreasureSystem treasureSystem;
  FrostEmberSystem frostEmberSystem;
  Player player;
  DeckComponent deckComponent;
  CardManager cardManager;
  DeckSystem deckSystem;
  private SpriteBatch sb;
  private BitmapFont font;
  private MapManager mapManager;
  private Engine engine;

  public MapManager mapManager() {
    return mapManager;
  }

  public DeckSystem deckSystem() {
    return deckSystem;
  }

  public CardManager cardManager() { return cardManager; }

  public DeckComponent deckComponent() {
    return deckComponent;
  }

  @Override
  public void create() {
    sb               = new SpriteBatch();
    font             = new BitmapFont();
    mapManager       = new MapManager();
    eventDispatcher  = new EventDispatcher();
    componentManager = new ComponentManager();
    cardManager      = new CardManager();
    deckSystem       = new DeckSystem(cardManager);
    treasureSystem   = new TreasureSystem(eventDispatcher);
    frostEmberSystem = new FrostEmberSystem(eventDispatcher);
    engine           = new Engine();

    engine.addSystem(new MovementSystem());


    for (int i = 0; i < 30; i++) {
      cardManager.addCard(new NormalCard());
    }

    deckComponent = new DeckComponent();

    mapManager.loadMap("crypt.tmx");

    setScreen(new MenuScreen(this, componentManager));
    gameScreen = new GameScreen(this, componentManager, eventDispatcher, treasureSystem, frostEmberSystem, deckSystem);
  }

  public void startGame() {
    setScreen(gameScreen);
  }

  public SpriteBatch getSb() {
    return sb;
  }

  @Override
  public void dispose() {
    sb.dispose();
    font.dispose();
  }

  @Override
  public void render() {
    super.render();
  }

  public BitmapFont font() {
    return font;
  }

  public Engine engine() {
    return engine;
  }
}
