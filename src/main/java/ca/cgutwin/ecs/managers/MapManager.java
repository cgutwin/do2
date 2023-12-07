package ca.cgutwin.ecs.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapManager {
  private TiledMap currentMap;
  private OrthogonalTiledMapRenderer mapRenderer;

  public MapManager() {
  }

  /**
   * Loads a map from a TMX file.
   *
   * @param mapPath The file path to the TMX map.
   */
  public void loadMap(String mapPath) {
    if (currentMap != null) {
      currentMap.dispose();  // Dispose of the current map if it exists
    }
    currentMap  = new TmxMapLoader().load(mapPath);
    mapRenderer = new OrthogonalTiledMapRenderer(currentMap);
  }

  /**
   * Renders the currently loaded map.
   */
  public void render(OrthographicCamera camera) {
    if (mapRenderer != null) {
      mapRenderer.setView(camera);
      mapRenderer.render();
    }
  }

  /**
   * Disposes of the current map and its renderer.
   */
  public void dispose() {
    if (currentMap != null) {
      currentMap.dispose();
    }
    if (mapRenderer != null) {
      mapRenderer.dispose();
    }
  }
}
