package ca.cgutwin.deckedout2.rendering;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent implements Component
{
  public TextureRegion texture;

  public RenderComponent(TextureRegion texture) {
    this.texture = texture;
  }
}
