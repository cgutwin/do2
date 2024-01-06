package ca.cgutwin.deckedout2.physics.components;

import ca.cgutwin.deckedout2.physics.collisions.CollisionHandler;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class CollisionComponent implements Component
{
  public Entity collidedEntity;
  public CollisionHandler handler;

  public CollisionComponent(CollisionHandler handler) {
    this.collidedEntity = null;
    this.handler        = handler;
  }
}
