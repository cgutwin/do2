package ca.cgutwin.deckedout.ui.systems;

import ca.cgutwin.deckedout.DeckSystem;
import ca.cgutwin.deckedout.card2.Card;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class CardsDrawnDisplay implements RenderableInterface {

  private final DeckSystem deckSystem;

  public CardsDrawnDisplay(DeckSystem deckSystem) {
    this.deckSystem = deckSystem;
  }

  @Override
  public void render(SpriteBatch sb) {
    List<Card> deck = deckSystem.deck();
    for (int i = 0; i < deck.size(); i++) {
      final Card card = deck.get(i);
      card.draw(sb, i);
    }
  }

  @Override
  public void update(float dt) {

  }

  @Override
  public void dispose() {

  }
}
