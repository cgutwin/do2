package ca.cgutwin.deckedout.ecs.components.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public final class Box2DComponent implements Component {
  private final Body body;

  public Box2DComponent(Body body) { this.body = body; }

  public Body body() { return body; }
}
