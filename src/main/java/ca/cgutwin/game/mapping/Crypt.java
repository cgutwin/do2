package ca.cgutwin.game.mapping;

import ca.cgutwin.game.core.tiles.SnowTile;
import ca.cgutwin.game.ecs.components.ChunkComponent;

public class Crypt extends GameMap {

  public Crypt(int mapWidth, int mapHeight) {
    super(mapWidth, mapHeight);
  }

  @Override
  protected void generateMap() {
    // Logic for generating a grassland map
    // You can use chunkManager to add and modify chunks
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        // Example: Create and add a chunk with grass tiles
        ChunkComponent chunk = new ChunkComponent(ChunkComponent.CHUNK_SIZE, ChunkComponent.CHUNK_SIZE);
        fillChunkWithTiles(chunk);
        getChunkManager().addChunk(x, y, chunk);
      }
    }
  }

  private void fillChunkWithTiles(ChunkComponent chunk) {
    for (int x = 0; x < ChunkComponent.CHUNK_SIZE; x++) {
      for (int y = 0; y < ChunkComponent.CHUNK_SIZE; y++) {
        chunk.setTile(x, y, new SnowTile());
      }
    }
  }
}
