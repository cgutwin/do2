package ca.cgutwin.cards.deck;

import ca.cgutwin.cards.Card;
import ca.cgutwin.cards.parser.json.CardName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class DeckTest {
  static List<Card> cards;

  @BeforeAll
  static void beforeAll() {
    cards = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      cards.add(new Card.Builder().withPrice(i).build());
    }
  }

  @Test
  @DisplayName("Deck should be created with the specified cards")
  public void testDeckCreation() {
    Deck deck = new Deck(cards);

    assertNotEquals(0, deck.size());
    assertEquals(cards, deck.getCards());
  }

  @Test
  @DisplayName("The first card should be returned when drawn")
  public void testDrawCardFromDeck() {
    Deck deck = new Deck(cards);
    Card firstCard = deck.getCards().peek();
    Card card = deck.draw();
    assertEquals(firstCard, card);
  }

  @Test
  @DisplayName("After shuffle, deck contains the same cards")
  public void testShuffleContainsSameCards() {
    Deck deck = new Deck(cards);
    Set<Card> originalCards = new HashSet<>(deck.getCards());

    deck.shuffle();
    Set<Card> shuffledCards = new HashSet<>(deck.getCards());

    assertEquals(originalCards, shuffledCards);
  }

  @Test
  @DisplayName("Card should be added to back of deck")
  public void testAddCard() {
    Deck deck = new Deck(cards);
    Card newCard = new Card.Builder().withName(new CardName.Builder().withLongName("test")).build();

    deck.addCard(newCard);
    List<Card> cardList = new ArrayList<>(deck.getCards());

    assertEquals(cardList.indexOf(newCard), deck.size()-1);
  }
}