package ca.cgutwin.cards.deck;

import ca.cgutwin.cards.Card;

import java.util.*;

public class Deck {
  private final Queue<Card> cards;

  public Deck(List<Card> cards) {
    this.cards = new LinkedList<>(cards);
  }

  public void shuffle() {
    List<Card> cardList = new ArrayList<>(cards);
    Collections.shuffle(cardList);
    cards.clear();
    cards.addAll(cardList);
  }

  public Card draw() {
    return cards.poll();
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public int size() {
    return cards.size();
  }

  public Queue<Card> getCards() {
    return cards;
  }
}
