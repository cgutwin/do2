package ca.cgutwin.deckedout2.rendering.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component
{
  public TextureRegion texture;

  public TextureComponent(TextureRegion texture) {
    this.texture = texture;
  }
}
