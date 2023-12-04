package ca.cgutwin.game.core.tiles;

public class TileGrid {
  private final Tile[][] tiles;
  private final int width;
  private final int height;

  public TileGrid(int width, int height) {
    this.width = width;
    this.height = height;
    this.tiles = new Tile[width][height];
  }

  public Tile getTile(int x, int y) {
    if (isWithinBounds(x, y)) {
      return tiles[x][y];
    }
    return null; // or throw an exception
  }

  public void setTile(int x, int y, Tile ITile) {
    if (isWithinBounds(x, y)) {
      tiles[x][y] = ITile;
    }
  }

  private boolean isWithinBounds(int x, int y) {
    return x >= 0 && y >= 0 && x < width && y < height;
  }

  // Additional methods as needed, like iteration over all tiles, etc.
}
