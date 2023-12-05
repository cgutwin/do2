package ca.cgutwin.game.ecs.components;

import ca.cgutwin.game.core.tiles.Tile;
import ca.cgutwin.game.core.tiles.TileGrid;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChunkComponent implements Component {
  public static int CHUNK_SIZE = 16;
  private final TileGrid tileGrid;

  public ChunkComponent(int width, int height) {
    tileGrid = new TileGrid(width, height);
  }

  public Tile getTile(int x, int y) {
    return tileGrid.getTile(x, y);
  }

  public void setTile(int x, int y, Tile tile) {
    tileGrid.setTile(x, y, tile);
  }

  public void render(SpriteBatch batch) {
    for (int x = 0; x < CHUNK_SIZE; x++) {
      for (int y = 0; y < CHUNK_SIZE; y++) {
        Tile tile = getTile(x, y);
        if (tile != null) {
          tile.render(batch, x*8, y*8);
        }
      }
    }
  }
}
