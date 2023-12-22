package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class HandComponent implements Component {
  private List<Card> cardsInHand;

  public HandComponent() {
    this.cardsInHand = new ArrayList<>();
  }

  public List<Card> cardsInHand() {
    return cardsInHand;
  }

  public void setCardsInHand(List<Card> cardsInDeck) {
    this.cardsInHand = cardsInDeck;
  }

  public int getHandSize() {
    return cardsInHand.size();
  }
}
