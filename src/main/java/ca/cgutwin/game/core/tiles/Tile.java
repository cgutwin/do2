package ca.cgutwin.game.core.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile implements ITile {
  protected static final int TILE_SIZE = 16;

  protected Texture texture; // The texture for rendering the tile

  public Tile(Texture texture) {
    this.texture = texture;
  }

  public abstract void render(SpriteBatch batch, int x, int y);

  public abstract void update(float delta);

  public boolean isWalkable() {
    return true;
  }

  public void dispose() {
    texture.dispose();
  }
}
