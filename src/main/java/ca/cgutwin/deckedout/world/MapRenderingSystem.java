package ca.cgutwin.deckedout.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapRenderingSystem {
  private final OrthogonalTiledMapRenderer mapRenderer;
  private final MapManager mapManager;
  private final OrthographicCamera camera;

  public MapRenderingSystem(MapManager mapManager, OrthographicCamera camera) {
    this.mapManager = mapManager;
    this.camera     = camera;

    this.mapRenderer = new OrthogonalTiledMapRenderer(mapManager.getCurrentMap());
  }

  public void update(float deltaTime) {
    mapRenderer.setView(camera);
    mapRenderer.render();
  }

  public void dispose() {
    mapRenderer.dispose();
  }
}
