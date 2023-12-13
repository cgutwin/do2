package ca.cgutwin.components;

import ca.cgutwin.cards.Card;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

public class DeckComponent implements IComponent {
  private final Queue<Card> cards;
  private final Map<String, List<Card>> cardMap;

  public DeckComponent(List<Card> cards) {
    this.cards   = new LinkedList<>(cards);
    this.cardMap = new HashMap<>();

    loadCardsIntoMap();
  }

  private void loadCardsIntoMap() {
    for (Card card: cards) {
      String shortName = card.getName().getShortName();

      if (!cardMap.containsKey(shortName)) {
        cardMap.put(shortName, new ArrayList<>(card.getLimit()));
      }

      cardMap.get(shortName).add(card);
    }
  }

  public DeckComponent() {
    this.cards   = new LinkedList<>();
    this.cardMap = new HashMap<>();
    loadCardsIntoMap();
  }

  public Map<String, List<Card>> getCardMap() {
    return cardMap;
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

  public void update() {
    loadCardsIntoMap();
  }

  public void render(SpriteBatch sb) {
    int i = 0;
    for (Card card: cards) {
      card.render(sb, i, 0);
      i += 10;
    }
  }
}
