package ca.cgutwin.deckedout2.rendering;

import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The RenderingSystem class is responsible for rendering entities with RenderableComponents.
 */
public class RenderingSystem extends EntitySystem
{
  private final SpriteBatch spriteBatch;
  private final ComponentMapper<RenderableComponent> renderableMapper;
  private ImmutableArray<Entity> entities;

  /**
   * Constructs a RenderingSystem instance with a SpriteBatch for rendering.
   *
   * @param spriteBatch The SpriteBatch used for rendering.
   */
  public RenderingSystem(SpriteBatch spriteBatch) {
    this.spriteBatch = spriteBatch;

    renderableMapper = ComponentMapper.getFor(RenderableComponent.class);
  }

  /**
   * Called when the RenderingSystem is added to the engine. Initializes the list of entities to render.
   *
   * @param engine The Ashley engine to which this system is added.
   */
  @Override
  public void addedToEngine(Engine engine) {
    // Get all entities with RenderableComponent
    entities = getEngine().getEntitiesFor(Family.all(RenderableComponent.class).get());
  }

  /**
   * Update method for rendering entities with RenderableComponents.
   *
   * @param deltaTime The time elapsed since the last frame.
   */
  @Override
  public void update(float deltaTime) {
    spriteBatch.begin();
    for (Entity entity: entities) {
      RenderableComponent renderable = renderableMapper.get(entity);

      if (renderable.renderer != null) {
        // Call the renderer's render method to render the entity
        renderable.renderer.render(entity, spriteBatch);
      }
    }

    spriteBatch.end();
  }
}
