package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.ecs.managers.MapManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapRenderingSystem {
  private final OrthogonalTiledMapRenderer mapRenderer;
  private final OrthographicCamera camera;

  public MapRenderingSystem(MapManager mapManager, OrthographicCamera camera) {
    this.camera = camera;

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
