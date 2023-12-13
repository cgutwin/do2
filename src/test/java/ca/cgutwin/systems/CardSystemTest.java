package ca.cgutwin.systems;

import ca.cgutwin.ECSManager;
import ca.cgutwin.cards.Card;
import ca.cgutwin.components.DeckComponent;
import ca.cgutwin.entities.Entity;
import org.junit.jupiter.api.Test;

class CardSystemTest {
  @Test
  void initCardSystem() {
    ECSManager ecsManager = new ECSManager();
    ecsManager.init();
    CardSystem cardSystem = (CardSystem) ecsManager.getSystems().getFirst();
    Entity player = ecsManager.getEntityManager().getPlayer();
    DeckComponent deck = ecsManager.getComponentManager().getComponent(player, DeckComponent.class);

    for (int i = 0; i < 3; i++) {
      deck.addCard(new Card.Builder().withPrice(i).build());
    }

    deck.update();
    cardSystem.initCardSystem(player);
  }
}