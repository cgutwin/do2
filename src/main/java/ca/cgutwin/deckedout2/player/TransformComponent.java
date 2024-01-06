package ca.cgutwin.deckedout2.player;

import ca.cgutwin.deckedout2.world.Position;
import com.badlogic.ashley.core.Component;

public class TransformComponent implements Component
{
  public Position position;

  public TransformComponent(float x, float y) {
    this.position = new Position(x, y);
  }

  public TransformComponent(Position initialPosition) {
    this.position = initialPosition;
  }
}
