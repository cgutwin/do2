package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.card2.Card;
import ca.cgutwin.deckedout.card2.CardManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class DeckSystem {
  private final CardManager cardManager;
  private final List<Card> deck;

  public DeckSystem(CardManager cardManager) {
    this.cardManager = cardManager;
    deck             = new ArrayList<>();
  }

  public List<Card> deck() {
    return deck;
  }

  public void saveSelectedCards(String saveName) {
    Preferences prefs = Gdx.app.getPreferences("MyGamePreferences");
    StringBuilder cardIds = new StringBuilder();

    for (Card card: deck) {
      cardIds.append(card.id()).append(",");
    }

    prefs.putString(saveName, cardIds.toString());
    prefs.flush();
  }

  public void loadSelectedCards(String saveName) {
    // TODO: Abstract out the loading of the cards from preferences.
    Preferences prefs = Gdx.app.getPreferences("MyGamePreferences");
    String savedCardIds = prefs.getString(saveName, "");
    Array<String> cardIds = new Array<>(savedCardIds.split(","));

    deck.clear();

    for (String cardIdString: cardIds) {
      int id = Integer.parseInt(cardIdString);
      List<Card> cardsWithId = cardManager.getCardsWithID(id);
      for (Card card: cardsWithId) {
        if (card != null) { deck.add(card); }
      }
    }
  }
}
