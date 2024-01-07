package ca.cgutwin.deckedout2.rendering.renderers;

import ca.cgutwin.deckedout2.rendering.Renderer;
import ca.cgutwin.deckedout2.rendering.StaticTileMapRenderer;
import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import ca.cgutwin.deckedout2.world.components.MapComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The MapRenderer class implements the Renderer interface and is responsible for rendering the game map.
 * It uses a StaticTileMapRenderer to render the map as static tiles.
 */
public class MapRenderer implements Renderer
{
  private final StaticTileMapRenderer staticTileMapRenderer;

  /**
   * Constructs a MapRenderer instance and initializes the StaticTileMapRenderer.
   */
  public MapRenderer() {
    staticTileMapRenderer = new StaticTileMapRenderer();
  }

  /**
   * Renders the map associated with the given entity using the provided SpriteBatch.
   *
   * @param entity      The entity containing the MapComponent and RenderableComponent.
   * @param spriteBatch The SpriteBatch used for rendering.
   */
  @Override
  public void render(Entity entity, SpriteBatch spriteBatch) {
    MapComponent mapComponent = entity.getComponent(MapComponent.class);
    RenderableComponent renderableComponent = entity.getComponent(RenderableComponent.class);

    // Set the map for the StaticTileMapRenderer if not already set
    if (staticTileMapRenderer.getMap() == null) {
      staticTileMapRenderer.setMap(mapComponent.map);
    }

    // Set the view of the StaticTileMapRenderer to match the camera
    staticTileMapRenderer.setView((OrthographicCamera) renderableComponent.camera);

    // Render the map using the StaticTileMapRenderer
    staticTileMapRenderer.render();
  }
}
