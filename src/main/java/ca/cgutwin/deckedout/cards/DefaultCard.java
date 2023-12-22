package ca.cgutwin.deckedout.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DefaultCard implements Card {
  int id;
  Texture texture;

  public DefaultCard(Texture texture, int id) {
    this.texture = texture;
    this.id      = id;
  }

  public Texture texture() {
    return texture;
  }

  public void draw(SpriteBatch sb) {
    sb.draw(texture, id*50, 0, 45, 60);
  }

  public int id() {
    return id;
  }
}
