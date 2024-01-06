package ca.cgutwin.deckedout2.physics.collisions;

import ca.cgutwin.deckedout2.physics.collisions.handlers.DefaultCollisionHandler;
import ca.cgutwin.deckedout2.physics.components.CollisionComponent;
import ca.cgutwin.deckedout2.physics.components.PhysicsComponent;
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
  private final Map<Class<? extends Component>, CollisionHandler> collisionHandlers;
  private final ComponentMapper<PhysicsComponent> bodyMapper;
  private final ComponentMapper<CollisionComponent> collisionMapper;

  public CollisionSystem(World world) {
    super(Family.all(CollisionComponent.class).get(), TIME_STEP);

    this.collisionHandlers = new HashMap<>();
    this.bodyMapper        = ComponentMapper.getFor(PhysicsComponent.class);
    this.collisionMapper   = ComponentMapper.getFor(CollisionComponent.class);

    setupCollisionHandlers();
    setupCollisionListener(world);
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
      System.out.println("collision");
      collisionComp.handler.handleCollision(entity, collisionComp.collidedEntity);
      collisionComp.collidedEntity = null;
    }
  }
}
