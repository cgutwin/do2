package ca.cgutwin.deckedout.card2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardManager {
  Map<Integer, List<Card>> cardsById;
  Map<CardTypeEnum, List<Card>> cardsByType;

  public CardManager() {
    this.cardsById   = new HashMap<>();
    this.cardsByType = new HashMap<>();
  }

  public void addCard(Card card) {
    int cardId = card.id();

    cardsById.putIfAbsent(cardId, new ArrayList<>());
    cardsByType.putIfAbsent(card.type(), new ArrayList<>());

    cardsById.get(cardId).add(card);
    cardsByType.get(card.type()).add(card);
  }

  public List<Card> getCardsWithID(int id) {
    return cardsById.get(id);
  }

  public Map<Integer, List<Card>> getCardsByID() {
    return cardsById;
  }

  public List<Card> getCardsWithType(CardTypeEnum type) {
    return cardsByType.get(type);
  }

  public Map<CardTypeEnum, List<Card>> getCardsByType() {
    return cardsByType;
  }
}
