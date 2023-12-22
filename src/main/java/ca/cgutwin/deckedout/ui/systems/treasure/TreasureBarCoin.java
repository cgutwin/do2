package ca.cgutwin.deckedout.ui.systems.treasure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TreasureBarCoin {
  private final TextureRegion texture;
  private final TextureAtlas atlas;

  public TreasureBarCoin() {
    this.atlas   = new TextureAtlas("coins.atlas");
    this.texture = atlas.findRegion("coin1");
  }

  public void draw(SpriteBatch sb, int x, int y) {
    sb.draw(texture, x, y);
  }
}
