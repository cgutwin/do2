package ca.cgutwin.deckedout.card2.cardSelectionScreen;

import ca.cgutwin.deckedout.DeckSystem;
import ca.cgutwin.deckedout.card2.Card;
import ca.cgutwin.deckedout.card2.CardManager;
import ca.cgutwin.deckedout.card2.CardTypeEnum;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.List;
import java.util.Map;

public class CardGrid {
  private final CardManager cardManager;
  private final DeckSystem deckSystem;
  private final ScrollPane scrollPane;
  private final Table table;

  public CardGrid(CardManager cardManager, DeckSystem deckSystem) {
    this.cardManager = cardManager;
    this.deckSystem  = deckSystem;
    this.table       = new Table();
    this.scrollPane  = new ScrollPane(table);

    render();
  }

  private void render() {
    Map<CardTypeEnum, List<Card>> cardsById = cardManager.getCardsByType();

    for (Map.Entry<CardTypeEnum, List<Card>> integerListEntry: cardsById.entrySet()) {
      CardTypeEnum key = integerListEntry.getKey();
      List<Card> value = integerListEntry.getValue();

      table.row();

      for (Card card: value) {
        TextureRegion textureRegion = new TextureRegion(card.texture());
        Drawable drawable = new TextureRegionDrawable(textureRegion);
        Drawable cardDrawableGreen = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal("cards/img.png"))));
        ImageButton cardButton = new ImageButton(drawable, drawable, cardDrawableGreen);

        cardButton.setName(Integer.toString(card.id()));

        table.add(cardButton).padRight(5F);

        cardButton.addListener(new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
            if (cardButton.isChecked()) {
              deckSystem.deck().add(card);
            }
            else { deckSystem.deck().remove(card); }

            System.out.println(deckSystem.deck().size());
          }
        });
      }
    }
  }

  public void update() {
    for (Card card: deckSystem.deck()) {
      ImageButton imageButton = table.findActor(Integer.toString(card.id()));
      imageButton.setChecked(true);
    }
  }

  public ScrollPane scrollPane() {
    return scrollPane;
  }
}
