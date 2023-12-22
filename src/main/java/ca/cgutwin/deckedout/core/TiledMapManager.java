package ca.cgutwin.deckedout.core;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledMapManager {
  private final TiledMap tiledMap;
  private final OrthogonalTiledMapRenderer renderer;

  public TiledMapManager(String mapPath) {
    tiledMap = new TmxMapLoader().load(mapPath);
    renderer = new OrthogonalTiledMapRenderer(tiledMap);
  }

  public void render() {
    renderer.render();
  }

  public TiledMap tiledMap() {
    return tiledMap;
  }

  public void dispose() {
    tiledMap.dispose();
    renderer.dispose();
  }
}
