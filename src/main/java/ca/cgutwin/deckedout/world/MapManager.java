package ca.cgutwin.deckedout.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapManager {
  private TiledMap currentMap;

  public void loadMap(String path) {
    if (currentMap != null) {
      currentMap.dispose(); // Dispose the old map
    }
    currentMap = new TmxMapLoader().load(path);
  }

  public TiledMap getCurrentMap() {
    return currentMap;
  }

  public void dispose() {
    if (currentMap != null) {
      currentMap.dispose();
    }
  }
}