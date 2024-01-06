package ca.cgutwin.deckedout2.physics.collisions;

import ca.cgutwin.deckedout2.physics.collisions.handlers.DefaultCollisionHandler;
import ca.cgutwin.deckedout2.physics.components.BodyComponent;
import ca.cgutwin.deckedout2.physics.components.CollisionComponent;
import ca.cgutwin.deckedout2.world.components.TileComponent;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.physics.box2d.*;

import java.util.HashMap;
import java.util.Map;

import static ca.cgutwin.deckedout2.physics.PhysicsSystem.TIME_STEP;

public class CollisionSystem extends IntervalIteratingSystem
{
  private final World world;
  private final Map<Class<? extends Component>, CollisionHandler> collisionHandlers;
  private ComponentMapper<BodyComponent> bodyMapper;
  private ComponentMapper<CollisionComponent> collisionMapper;

  public CollisionSystem(World world) {
    super(Family.all(BodyComponent.class, CollisionComponent.class).get(), TIME_STEP);

    this.world             = world;
    this.collisionHandlers = new HashMap<>();

    setupCollisionHandlers();
    setupCollisionListener(this.world);
  }

  private void setupCollisionHandlers() {
    collisionHandlers.put(TileComponent.class, new DefaultCollisionHandler());
  }

  private void setupCollisionListener(World world) {
    world.setContactListener(new ContactListener()
    {
      @Override
      public void beginContact(Contact contact) {
        Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
        Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();

        CollisionHandler handler = findCollisionHandler(entityA, entityB);
        if (handler != null) {
          handler.handleCollision(entityA, entityB);
        }
      }

      @Override
      public void endContact(Contact contact) { }

      @Override
      public void preSolve(Contact contact, Manifold oldManifold) { }

      @Override
      public void postSolve(Contact contact, ContactImpulse impulse) { }

      private CollisionHandler findCollisionHandler(Entity entityA, Entity entityB) {
        for (Class<? extends Component> key: collisionHandlers.keySet()) {
          if (ComponentMapper.getFor(key).has(entityA) || ComponentMapper.getFor(key).has(entityB)) {
            return collisionHandlers.get(key);
          }
        }
        return new DefaultCollisionHandler();
      }
    });
  }

  @Override
  protected void processEntity(Entity entity) {
    CollisionComponent collisionComp = collisionMapper.get(entity);

    if (collisionComp.collidedEntity != null && collisionComp.handler != null) {
      collisionComp.handler.handleCollision(entity, collisionComp.collidedEntity);
      collisionComp.collidedEntity = null;
    }
  }
}
