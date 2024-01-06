package ca.cgutwin.deckedout2.physics.collisions;

import com.badlogic.ashley.core.Entity;

public interface CollisionHandler
{
  void handleCollision(Entity entityA, Entity entityB);
}
