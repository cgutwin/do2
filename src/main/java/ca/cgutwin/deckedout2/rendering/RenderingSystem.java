package ca.cgutwin.deckedout2.rendering;

import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderingSystem extends EntitySystem
{
  private final SpriteBatch spriteBatch;
  private final ComponentMapper<RenderableComponent> renderableMapper;
  private ImmutableArray<Entity> entities;

  public RenderingSystem(SpriteBatch spriteBatch) {
    this.spriteBatch = spriteBatch;
    renderableMapper = ComponentMapper.getFor(RenderableComponent.class);
  }

  @Override
  public void addedToEngine(Engine engine) {
    entities = getEngine().getEntitiesFor(Family.all(RenderableComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    spriteBatch.begin();
    for (Entity entity: entities) {
      RenderableComponent renderable = renderableMapper.get(entity);

      if (renderable.renderer != null) {
        renderable.renderer.render(entity, spriteBatch);
      }
    }
    spriteBatch.end();
  }
}
