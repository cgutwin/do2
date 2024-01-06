package ca.cgutwin.deckedout2.rendering;

import ca.cgutwin.deckedout2.world.components.TileComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileEntityRenderingSystem extends RenderingSystem
{
  public TileEntityRenderingSystem(SpriteBatch spriteBatch) {
    super(spriteBatch);
  }

  @Override
  protected void render(Entity entity, SpriteBatch batch) {
    RenderComponent render = entity.getComponent(RenderComponent.class);
    TileComponent tile = entity.getComponent(TileComponent.class);

    batch.draw(render.texture, tile.position.x*render.texture.getRegionWidth(),
               tile.position.y*render.texture.getRegionHeight());
  }

  @Override
  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.all(RenderComponent.class).get());
  }
}
