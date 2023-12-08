package ca.cgutwin.deckedout.game.ecs.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent implements Component {
  public TextureRegion textureRegion;

  public RenderComponent(TextureRegion textureRegion) {
    this.textureRegion = textureRegion;
  }
}
