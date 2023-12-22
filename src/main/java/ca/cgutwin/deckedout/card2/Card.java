package ca.cgutwin.deckedout.card2;

import ca.cgutwin.deckedout.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Card extends Entity implements CardInterface {
  CardTypeEnum type;
  Texture texture;

  public Card(Texture texture) {
    this.texture = texture;
  }

  public Texture texture() {
    return texture;
  }

  public CardTypeEnum type() {
    return type;
  }

  public abstract void applyCardEffect();

  @Override
  public abstract void draw(SpriteBatch sb, int i);
}
