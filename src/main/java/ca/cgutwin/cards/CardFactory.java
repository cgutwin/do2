package ca.cgutwin.cards;

import ca.cgutwin.cards.parser.json.CardType;

public class CardFactory {
  public static Card createCard(Card.Builder cardBuilder) {
    CardType cardType = cardBuilder.type;
    Card.Builder card;

    if (cardType == CardType.ETHEREAL) {
      card = new EtherealCard.Builder().withType(CardType.ETHEREAL);
    }
    else if (cardType == CardType.PERMANENT) {
      card = new PermanentCard.Builder().withType(CardType.PERMANENT);
    }
    else {
      card = new Card.Builder().withType(CardType.NORMAL);
    }

    return card.withName(cardBuilder.nameBuilder).withPrice(cardBuilder.price).build();
  }
}
