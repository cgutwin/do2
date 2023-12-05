package ca.cgutwin.game.mapping;

import ca.cgutwin.game.ecs.components.ChunkComponent;
import ca.cgutwin.game.ecs.managers.ChunkManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameMap {
  protected final int width;
  protected final int height;
  private final ChunkManager chunkManager;

  public GameMap(int width, int height) {
    this.width        = width;
    this.height       = height;
    this.chunkManager = new ChunkManager(ChunkComponent.CHUNK_SIZE);
  }

  protected abstract void generateMap();

  // Method to access the chunkManager
  public ChunkManager getChunkManager() {
    return chunkManager;
  }

  public void render(SpriteBatch batch) {
    chunkManager.render(batch);
  }
}
