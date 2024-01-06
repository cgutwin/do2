package ca.cgutwin.deckedout2.rendering.components;

import ca.cgutwin.deckedout2.rendering.Renderer;
import com.badlogic.ashley.core.Component;

public class RenderableComponent implements Component
{
  public Renderer renderer;

  public RenderableComponent(Renderer renderer) {
    this.renderer = renderer;
  }
}
