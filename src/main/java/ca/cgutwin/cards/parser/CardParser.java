package ca.cgutwin.cards.parser;

import ca.cgutwin.cards.Card;
import ca.cgutwin.cards.CardFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CardParser {
  private final ObjectMapper objectMapper;

  public CardParser() {
    this.objectMapper = new ObjectMapper();
  }

  public Card parseCard(String json) throws IOException {
    Card.Builder cardBuilder = objectMapper.readValue(json, Card.Builder.class);
    return CardFactory.createCard(cardBuilder);
  }

  public List<Card> parseAllCardsInDirectory(String directoryPath) throws IOException {
    List<Card> cards = new ArrayList<>();
    Path dir = Paths.get(directoryPath);

    DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.json");

    //noinspection TryFinallyCanBeTryWithResources
    try {
      for (Path entry: stream) {
        Card card = parseCardFromFile(entry.toString());
        cards.add(card);
      }
    }
    finally {
      stream.close();
    }

    return cards;
  }

  public Card parseCardFromFile(String filePath) throws IOException {
    return objectMapper.readValue(new File(filePath), Card.class);
  }
}
