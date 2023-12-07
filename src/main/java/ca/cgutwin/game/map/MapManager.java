package ca.cgutwin.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapManager {
  private TiledMap currentMap;
  private OrthogonalTiledMapRenderer mapRenderer;

  private final OrthographicCamera camera;

  public MapManager(OrthographicCamera camera) {
    this.camera = camera;
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
  public void render() {
    // Assuming you have a camera set up, update and render the map
    mapRenderer.setView(camera);
    mapRenderer.render();
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
