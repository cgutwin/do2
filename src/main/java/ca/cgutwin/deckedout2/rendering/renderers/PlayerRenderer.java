package ca.cgutwin.deckedout2.rendering.renderers;

import ca.cgutwin.deckedout2.player.TransformComponent;
import ca.cgutwin.deckedout2.rendering.Renderer;
import ca.cgutwin.deckedout2.rendering.components.TextureComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerRenderer implements Renderer
{
  @Override
  public void render(Entity entity, SpriteBatch spriteBatch) {
    TextureComponent texture = ComponentMapper.getFor(TextureComponent.class).get(entity);
    TransformComponent transform = ComponentMapper.getFor(TransformComponent.class).get(entity);

    if (transform != null && texture != null && texture.texture != null) {
      spriteBatch.draw(texture.texture, transform.position.x, transform.position.y);
    }
  }
}
