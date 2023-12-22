package ca.cgutwin.deckedout.ecs.components.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
  public final float velocityModifier;
  public Vector2 velocity = new Vector2();

  public VelocityComponent(float x, float y, float velocityModifier) {
    this.velocityModifier = velocityModifier;
    velocity.set(x, y);
  }
}
