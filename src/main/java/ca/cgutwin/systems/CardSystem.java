package ca.cgutwin.systems;

import ca.cgutwin.cards.Card;
import ca.cgutwin.components.ComponentManager;
import ca.cgutwin.components.DeckComponent;
import ca.cgutwin.components.HandComponent;
import ca.cgutwin.entities.Entity;

import java.util.List;
import java.util.Map;

public class CardSystem implements ISystem {
  private final ComponentManager componentManager;

  public CardSystem(ComponentManager componentManager) {
    this.componentManager = componentManager;
  }

  public void drawCard(Entity entity) {
    HandComponent handComponent = componentManager.getComponent(entity, HandComponent.class);

    if (handComponent != null) {
      Card card = handComponent.removeCard();
      System.out.println("drawn");
      System.out.println(card.toString());
    }
  }

  public void initCardSystem(Entity entity) {
    DeckComponent deckComponent = componentManager.getComponent(entity, DeckComponent.class);
    HandComponent handComponent = componentManager.getComponent(entity, HandComponent.class);
    if (deckComponent == null || handComponent == null) { return; }

    loadDeckIntoHand(entity);
    handComponent.shuffleHand();
  }

  private void loadDeckIntoHand(Entity entity) {
    DeckComponent deckComponent = componentManager.getComponent(entity, DeckComponent.class);
    HandComponent handComponent = componentManager.getComponent(entity, HandComponent.class);
    if (deckComponent == null || handComponent == null) { return; }

    Map<String, List<Card>> cardMap = deckComponent.getCardMap();
    for (List<Card> cardList: cardMap.values()) {
      for (Card card: cardList) {
        handComponent.addCard(card);
      }
    }
  }

  /**
   *
   */
  @Override
  public void init() {
  }

  /**
   * @param dt
   */
  @Override
  public void update(float dt) {

  }

  /**
   * @param dt
   */
  @Override
  public void render(float dt) {

  }

  /**
   *
   */
  @Override
  public void dispose() {

  }
}
