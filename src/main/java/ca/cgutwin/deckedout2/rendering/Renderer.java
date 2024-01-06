package ca.cgutwin.deckedout2.rendering;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderer
{
  void render(Entity entity, SpriteBatch spriteBatch);
}
