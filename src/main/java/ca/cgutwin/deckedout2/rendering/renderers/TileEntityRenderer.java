package ca.cgutwin.deckedout2.rendering.renderers;

import ca.cgutwin.deckedout2.rendering.Renderer;
import ca.cgutwin.deckedout2.rendering.components.TextureComponent;
import ca.cgutwin.deckedout2.world.components.TileComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileEntityRenderer implements Renderer
{
  @Override
  public void render(Entity entity, SpriteBatch spriteBatch) {
    TextureComponent render = entity.getComponent(TextureComponent.class);
    TileComponent tile = entity.getComponent(TileComponent.class);

    spriteBatch.draw(render.texture, tile.position.x*render.texture.getRegionWidth(),
                     tile.position.y*render.texture.getRegionHeight());
  }
}
