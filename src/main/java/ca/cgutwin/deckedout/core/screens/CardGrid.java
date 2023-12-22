package ca.cgutwin.deckedout.core.screens;

import ca.cgutwin.deckedout.cards.Card;
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

import java.util.ArrayList;
import java.util.List;

public class CardGrid {
  private final Table table;
  private final ScrollPane scrollPane;
  List<Card> cards;
  List<ImageButton> actorButtons;

  public CardGrid(List<Card> cards) {
    this.cards   = cards;
    this.table   = new Table();
    actorButtons = new ArrayList<>();

    for (int i = 0, cardsSize = cards.size(); i < cardsSize; i++) {
      final Card card = cards.get(i);

      ImageButton cardButton = createCardActor(card);
      cardButton.setName(Integer.toString(card.id()));

      actorButtons.add(cardButton);
    }
    this.updateCardLayout(table.getWidth());

    this.scrollPane = new ScrollPane(table);
    this.scrollPane.setScrollingDisabled(true, false);
  }

  private ImageButton createCardActor(Card card) {
    Drawable cardDrawable = new TextureRegionDrawable(new TextureRegion(card.texture()));
    Drawable cardDrawableGreen = new TextureRegionDrawable(
            new TextureRegion(new Texture(Gdx.files.internal("cards/img.png"))));

    ImageButton cardButton = new ImageButton(cardDrawable, cardDrawable, cardDrawableGreen);

    cardButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println(cardButton.isChecked());
        System.out.println("click");
      }
    });

    return cardButton;
  }

  public void updateCardLayout(float width) {
    float cardWidth = 100; // Width of each card
    float padding = 10; // Padding between cards

    int cardsPerRow = Math.max(1, (int) ((width - padding)/(cardWidth + padding)));

    table.clear();
    int cardCount = 0;
    for (ImageButton imageButton: actorButtons) {
      table.add(imageButton).width(cardWidth).height(150).pad(padding);
      if (++cardCount%cardsPerRow == 0) {
        table.row();
      }
    }
  }

  public ScrollPane getScrollPane() {
    return scrollPane;
  }
}
