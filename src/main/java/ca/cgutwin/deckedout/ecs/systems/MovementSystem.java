package ca.cgutwin.deckedout.ecs.systems;

import ca.cgutwin.deckedout.ecs.components.InputComponent;
import ca.cgutwin.deckedout.ecs.components.physics.Box2DComponent;
import ca.cgutwin.deckedout.ecs.components.physics.PositionComponent;
import ca.cgutwin.deckedout.ecs.components.physics.VelocityComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementSystem extends IteratingSystem {
  private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
  private final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
  private final ComponentMapper<Box2DComponent> box2DMapper = ComponentMapper.getFor(Box2DComponent.class);
  private final ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);

  public MovementSystem() {
    super(Family.all(PositionComponent.class, VelocityComponent.class, Box2DComponent.class, InputComponent.class)
                .get());
  }

  @Override
  protected void processEntity(Entity entity, float deltaTime) {
    PositionComponent positionComponent = positionMapper.get(entity);
    VelocityComponent velocityComponent = velocityMapper.get(entity);
    Box2DComponent box2DComponent = box2DMapper.get(entity);
    InputComponent inputComponent = inputMapper.get(entity);

    Vector2 velocity = new Vector2(0, 0);
    float speed = 20F*(inputComponent.sprint ? velocityComponent.velocityModifier : 1);

    if (inputComponent.up) {
      velocity.y = speed;
    }
    else if (inputComponent.down) {
      velocity.y = -speed;
    }

    if (inputComponent.left) {
      velocity.x = -speed;
    }
    else if (inputComponent.right) {
      velocity.x = speed;
    }

    Body body = box2DComponent.body();

    body.setLinearVelocity(velocity);
    // Update PositionComponent based on Box2D body position
    positionComponent.position.set(body.getPosition());
    // Update VelocityComponent based on Box2D body velocity
    velocityComponent.velocity.set(body.getLinearVelocity());
  }
}