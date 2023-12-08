package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.ecs.components.MovementComponent;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.managers.EntityManager;
import ca.cgutwin.deckedout.game.events.ClankGameEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem implements System {
  private final EntityManager entityManager = EntityManager.getInstance();
  private final int playerEntityId; // ID of the player entity
  private final EventSystem eventSystem;

  public InputSystem(int playerEntityId, EventSystem eventSystem) {
    this.playerEntityId = playerEntityId;
    this.eventSystem    = eventSystem;
  }

  @Override
  public void update(float deltaTime) {
    movePlayer(deltaTime);
  }

  public void movePlayer(float dT) {
    Entity entity = entityManager.getEntityById(playerEntityId);
    MovementComponent movement = entity.getComponent(MovementComponent.class);

    if (movement == null) { return; }

    // Reset movement to zero at the beginning of each frame
    movement.velocityX = 0;
    movement.velocityY = 0;

    float speed;

    if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
      speed = movement.sprintSpeed;
      eventSystem.dispatchEventEvery(2F, new ClankGameEvent(1), dT);
    }
    else {
      speed = movement.speed;
    }

    // Process input and update movement component
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      movement.velocityX = -speed;
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      movement.velocityX = speed;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      movement.velocityY = speed;
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      movement.velocityY = -speed;
    }
  }
}
