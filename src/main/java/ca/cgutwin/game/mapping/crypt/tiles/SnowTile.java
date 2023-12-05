package ca.cgutwin.game.mapping.crypt.tiles;

import ca.cgutwin.game.core.tiles.Tile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SnowTile extends Tile {
  public SnowTile(TextureRegion texture) {
    super(texture);
  }

  @Override
  public void render(SpriteBatch batch, int x, int y) {
    batch.draw(texture, x, y, 32F, 32F);
  }

  @Override
  public void update(float delta) {

  }

  @Override
  public void render(SpriteBatch batch) {

  }
}
