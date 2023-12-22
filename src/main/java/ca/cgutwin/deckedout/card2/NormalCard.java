package ca.cgutwin.deckedout.card2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NormalCard extends Card {
  public NormalCard() {
    super(new Texture(Gdx.files.internal("cards/cardBack.png")));
  }

  @Override
  public void applyCardEffect() {
    System.out.println("effect");
  }

  @Override
  public void draw(SpriteBatch sb, int i) {
    sb.draw(texture, i*(texture.getWidth()*0.3F), 200, texture.getWidth()*0.3F, texture.getHeight()*0.3F);
  }

  @Override
  public void draw(SpriteBatch sb) {
  }
}
