package ca.cgutwin.game.core.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnowTile extends Tile {
  private static final Texture texture;

  static {
    texture = new Texture(Gdx.files.internal("img_1.png"));
  }

  public SnowTile() {
    super(texture);
  }

  @Override
  public void render(SpriteBatch batch, int x, int y) {
    batch.draw(texture, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
  }

  @Override
  public void update(float delta) {

  }

  @Override
  public void render(SpriteBatch batch) {

  }
}
