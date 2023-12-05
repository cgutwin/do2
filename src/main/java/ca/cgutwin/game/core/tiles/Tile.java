package ca.cgutwin.game.core.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile implements ITile {
  protected static final int TILE_SIZE = 16;

  protected TextureRegion texture; // The texture for rendering the tile

  public Tile(TextureRegion texture) {
    this.texture = texture;
  }

  public abstract void render(SpriteBatch batch, int x, int y);

  public abstract void update(float delta);

  public boolean isWalkable() {
    return true;
  }

  public void dispose() {
    texture.getTexture().dispose();
  }
}
