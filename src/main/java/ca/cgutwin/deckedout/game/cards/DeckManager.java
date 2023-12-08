package ca.cgutwin.deckedout.game.cards;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeckManager {
  private final Stack<Card> cards;

  public DeckManager(List<Card> initialCards) {
    cards = new Stack<>();
    cards.addAll(initialCards);
    shuffle();
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public Card drawCard() {
    return cards.isEmpty() ? null : cards.pop();
  }
}
