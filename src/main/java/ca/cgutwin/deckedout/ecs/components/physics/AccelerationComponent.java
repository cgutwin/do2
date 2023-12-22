package ca.cgutwin.deckedout.ecs.components.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class AccelerationComponent implements Component {
  public Vector2 acceleration = new Vector2();

  public AccelerationComponent(float x, float y) {
    acceleration.set(x, y);
  }
}
