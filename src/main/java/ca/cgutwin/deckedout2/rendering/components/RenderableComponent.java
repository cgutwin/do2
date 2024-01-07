package ca.cgutwin.deckedout2.rendering.components;

import ca.cgutwin.deckedout2.rendering.Renderer;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Camera;

/**
 * The RenderableComponent class represents a component that associates an entity with a renderer and a camera.
 * It is used to specify how an entity should be rendered and which camera to use for rendering.
 */
public class RenderableComponent implements Component
{
  /**
   * The renderer responsible for rendering the entity.
   */
  public Renderer renderer;

  /**
   * The camera used for rendering the entity.
   */
  public Camera camera;

  /**
   * Constructs a RenderableComponent with the specified renderer.
   *
   * @param renderer The renderer responsible for rendering the entity.
   */
  public RenderableComponent(Renderer renderer) {
    this.renderer = renderer;
  }
}
