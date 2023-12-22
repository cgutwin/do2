package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class DeckComponent implements Component {
  private List<Card> cardsInDeck;

  public DeckComponent() {
    this.cardsInDeck = new ArrayList<>();
  }

  public DeckComponent(List<Card> withCards) {
    this.cardsInDeck = new ArrayList<>(withCards);
  }

  public List<Card> cardsInDeck() {
    return cardsInDeck;
  }

  public void getCardByID(int id) {

  }

  public void setCardsInDeck(List<Card> cardsInDeck) {
    this.cardsInDeck = cardsInDeck;
  }

  public int getDeckSize() {
    return cardsInDeck.size();
  }
}
