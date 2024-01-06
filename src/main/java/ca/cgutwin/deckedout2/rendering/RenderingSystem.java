package ca.cgutwin.deckedout2.rendering;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class RenderingSystem extends EntitySystem
{
  protected final SpriteBatch spriteBatch;
  protected ImmutableArray<Entity> entities;

  public RenderingSystem(SpriteBatch spriteBatch) {
    this.spriteBatch = spriteBatch;
  }

  @Override
  public void update(float deltaTime) {
    spriteBatch.begin();
    for (Entity entity: entities) {
      render(entity, spriteBatch);
    }
    spriteBatch.end();
  }

  protected abstract void render(Entity entity, SpriteBatch batch);
}
