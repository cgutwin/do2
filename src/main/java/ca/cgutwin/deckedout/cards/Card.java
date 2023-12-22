package ca.cgutwin.deckedout.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Card {
  Texture texture();

  void draw(SpriteBatch sb);

  int id();
}
