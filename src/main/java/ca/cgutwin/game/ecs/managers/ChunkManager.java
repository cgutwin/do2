package ca.cgutwin.game.ecs.managers;

import ca.cgutwin.game.ecs.components.ChunkComponent;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public class ChunkManager {
  private Map<Point, ChunkComponent> chunks;
  private final int chunkSize;

  public ChunkManager(int chunkSize) {
    this.chunkSize = chunkSize;
    this.chunks = new HashMap<>();
  }

  public ChunkComponent getChunk(int chunkX, int chunkY) {
    return chunks.get(new Point(chunkX, chunkY));
  }

  public void addChunk(int chunkX, int chunkY, ChunkComponent chunk) {
    chunks.put(new Point(chunkX, chunkY), chunk);
  }

  // Method to load/generate a chunk
  public void loadChunk(int chunkX, int chunkY) {
    ChunkComponent chunk = new ChunkComponent(chunkSize, chunkSize);
    addChunk(chunkX, chunkY, chunk);
  }

  public void render(SpriteBatch batch) {
    for (ChunkComponent chunk : chunks.values()) {
      chunk.render(batch);
    }
  }
}
