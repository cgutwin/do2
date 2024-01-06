package ca.cgutwin.deckedout2.rendering.renderers;

import ca.cgutwin.deckedout2.rendering.Renderer;
import ca.cgutwin.deckedout2.rendering.StaticTileMapRenderer;
import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import ca.cgutwin.deckedout2.world.components.MapComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapRenderer implements Renderer
{
  StaticTileMapRenderer staticTileMapRenderer;

  public MapRenderer() {
    staticTileMapRenderer = new StaticTileMapRenderer();
  }

  @Override
  public void render(Entity entity, SpriteBatch spriteBatch) {
    MapComponent mapComponent = entity.getComponent(MapComponent.class);
    RenderableComponent renderableComponent = entity.getComponent(RenderableComponent.class);
    if (staticTileMapRenderer.getMap() == null) { staticTileMapRenderer.setMap(mapComponent.map); }

    staticTileMapRenderer.setView((OrthographicCamera) renderableComponent.camera);
    staticTileMapRenderer.render();
  }
}
