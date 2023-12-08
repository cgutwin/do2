package ca.cgutwin.deckedout.game.ecs.managers;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapManager {

  private TiledMap currentMap;

  public MapManager() { }

  /**
   * Loads a map and sets it as the current map.
   *
   * @param mapPath The file path to the TMX map.
   */
  public void loadMap(String mapPath) {
    if (currentMap != null) {
      currentMap.dispose(); // Dispose of the current map if it exists
    }
    currentMap = new TmxMapLoader().load(mapPath);
  }

  /**
   * Gets the current loaded map.
   *
   * @return The currently loaded TiledMap.
   */
  public TiledMap getCurrentMap() {
    return currentMap;
  }

  /**
   * Retrieves properties of the current map.
   *
   * @return The properties of the current map.
   */
  public MapProperties getMapProperties() {
    if (currentMap != null) {
      return currentMap.getProperties();
    }
    return null;
  }

  public boolean isTileWalkable(float x, float y) {
    TiledMapTileLayer collisionLayer = (TiledMapTileLayer) currentMap.getLayers().get("CollisionLayer");
    if (collisionLayer == null) return true;

    // Convert world coordinates (e.g., pixels) to tile coordinates
    int tileWidth = collisionLayer.getTileWidth();
    int tileHeight = collisionLayer.getTileHeight();

    float tileX = x / tileWidth;
    float tileY = y / tileHeight;

    TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (tileX + 0.5F), (int) (tileY + 0.5F));

    return cell == null;
  }

  /**
   * Disposes of the current map.
   */
  public void dispose() {
    if (currentMap != null) {
      currentMap.dispose();
    }
  }
}
