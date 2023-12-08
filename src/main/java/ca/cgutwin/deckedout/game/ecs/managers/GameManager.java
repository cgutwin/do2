package ca.cgutwin.deckedout.game.ecs.managers;

import ca.cgutwin.deckedout.game.cards.Card;
import ca.cgutwin.deckedout.game.cards.DeckManager;

public class GameManager {
  private DeckManager deck;
  private GameStateManager stateManager;

  public GameManager() {
  }

  public void update(float dT) {

  }

  private void drawCard() {
    Card card = deck.drawCard();
    if (card != null) {
      stateManager.addCard(card);
      // Apply the card's effect if necessary
      card.applyEffect(stateManager);
    }
  }
}
