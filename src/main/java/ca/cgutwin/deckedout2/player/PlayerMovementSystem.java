package ca.cgutwin.deckedout2.player;

import ca.cgutwin.deckedout2.physics.components.PhysicsComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;

public class PlayerMovementSystem extends EntitySystem
{
  private final ComponentMapper<PlayerControlComponent> controlMapper;
  private final ComponentMapper<PhysicsComponent> physicsMapper;

  public PlayerMovementSystem() {
    controlMapper = ComponentMapper.getFor(PlayerControlComponent.class);
    physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
  }

  @Override
  public void update(float deltaTime) {
    ImmutableArray<Entity> family = getEngine().getEntitiesFor(
            Family.all(PlayerComponent.class, PlayerControlComponent.class, PhysicsComponent.class).get());

    for (Entity entity: family) {
      PlayerControlComponent control = controlMapper.get(entity);
      PhysicsComponent physics = physicsMapper.get(entity);

      handleInput(control, physics.body);
    }
  }

  private void handleInput(PlayerControlComponent control, Body body) {
    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      control.upCommand.execute(body);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      control.leftCommand.execute(body);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      control.downCommand.execute(body);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      control.rightCommand.execute(body);
    }
  }
}
