package ca.cgutwin.components;

import ca.cgutwin.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandComponent implements IComponent {
  public static final int MAX_SIZE = 32;
  List<Card> hand;

  public HandComponent() {
    this.hand = new ArrayList<>(MAX_SIZE);
  }

  public void addCard(Card card) {
    hand.add(card);
  }

  public List<Card> getHand() {
    return hand;
  }

  public boolean canAddCard() {
    return hand.size() < MAX_SIZE;
  }

  public Card draw() {
    return hand.removeFirst();
  }

  public Card removeCard() {
    Card toRemove = hand.getFirst();
    hand.remove(toRemove);
    return toRemove;
  }

  public void shuffleHand() {
    Collections.shuffle(hand);
  }
}
