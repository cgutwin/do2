package ca.cgutwin;

import ca.cgutwin.cards.Card;
import ca.cgutwin.cards.EtherealCard;
import ca.cgutwin.cards.PermanentCard;
import ca.cgutwin.cards.parser.CardParser;
import ca.cgutwin.cards.parser.json.CardType;
import ca.cgutwin.cards.parser.json.Rarity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardParserTest {
  @Test
  @DisplayName("Ethereal Cards should be instanceof EtherealCard")
  public void testParseEtherealCard() throws IOException {
    String json = """
                  {
                    "name": {
                      "longName": "Moment of Clarity",
                      "shortName": "MOC"
                    },
                    "type": 1
                  }
                  """;

    CardParser parser = new CardParser();
    Card card = parser.parseCard(json);

    assertInstanceOf(EtherealCard.class, card);
    assertEquals(CardType.ETHEREAL, card.getType());
  }

  @Test
  @DisplayName("Permanent cards should be instanceof PermanentCard")
  public void testParsePermanentCard() throws IOException {
    String json = """
                  {
                    "name": {
                      "longName": "Suit Up",
                      "shortName": "SUU"
                    },
                    "type": 2
                  }
                  """;

    CardParser parser = new CardParser();
    Card card = parser.parseCard(json);

    assertInstanceOf(PermanentCard.class, card);
    assertEquals(CardType.PERMANENT, card.getType());
  }

  @Test
  @DisplayName("Cards should parse into a Card object from a JSON string")
  public void testParseCardFromString() throws IOException {
    String json = """
                  {
                    "name": {
                      "longName": "Sneak",
                      "shortName": "SNE"
                    },
                    "type": 0,
                    "price": 7,
                    "rarity": 0,
                    "limit": 5,
                    "queues": {
                      "blocks": {
                        "clank": 2,
                        "hazard": 0
                      },
                      "drops": {
                        "embers": 0,
                        "treasure": 0
                      }
                    },
                    "effects": {
                      "speed": false,
                      "jump": false,
                      "regen": false
                    },
                    "negatives": {
                      "clank": 0,
                      "hazard": 0
                    },
                    "deck_actions": {
                      "recycle": 0,
                      "stumble": 0
                    }
                  }
                  """;

    CardParser parser = new CardParser();
    Card card = parser.parseCard(json);

    assertEquals("Sneak", card.getName().getLongName());
  }

  @Test
  @DisplayName("Parser should turn a JSON file into a Card")
  public void testParseCardFromFile() throws IOException {
    String filePath = "src/test/resources/cards/testCard.json";
    CardParser parser = new CardParser();

    Card card = parser.parseCardFromFile(filePath);

    assertNotNull(card);
    assertFalse(card.getDeckActions().getStumble() != 0);
    assertEquals(card.getRarity(), Rarity.COMMON);
  }

  @Test
  @DisplayName("Parser should turn all JSON files in a directory into Cards")
  public void testParseAllCardsInDirectory() throws IOException {
    // Assume test directory contains multiple valid JSON files for cards
    String directoryPath = "src/test/resources/cards";
    CardParser parser = new CardParser();

    List<Card> cards = parser.parseAllCardsInDirectory(directoryPath);

    assertFalse(cards.isEmpty());
  }

  @Test
  @DisplayName("Parser should throw an IOException when the directory to search doesn't exist")
  public void testDirectoryNotFound() {
    String directoryPath = "src/test/resources/nocards";
    CardParser parser = new CardParser();

    assertThrows(IOException.class, ()->parser.parseAllCardsInDirectory(directoryPath));
  }


  @Test
  @DisplayName("Parser should throw an IOException when invalid JSON is passed")
  public void testErrorHandling() {
    String invalidJson = "{invalid json}";
    CardParser parser = new CardParser();

    assertThrows(IOException.class, ()->parser.parseCard(invalidJson));
  }
}