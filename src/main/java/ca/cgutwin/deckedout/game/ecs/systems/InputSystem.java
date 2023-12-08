package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.ecs.components.MovementComponent;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.managers.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem implements System {
  private final EntityManager entityManager = EntityManager.getInstance();
  private final int playerEntityId; // ID of the player entity

  public InputSystem(int playerEntityId) {
    this.playerEntityId = playerEntityId;
  }

  @Override
  public void update(float deltaTime) {
    movePlayer(deltaTime);
  }

  public void movePlayer(float dT) {
    Entity entity = entityManager.getEntityById(playerEntityId);
    MovementComponent movement = entity.getComponent(MovementComponent.class);

    if (movement == null) return;

    // Reset movement to zero at the beginning of each frame
    movement.velocityX = 0;
    movement.velocityY = 0;

    // Process input and update movement component
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      movement.velocityX = -movement.speed;
    } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      movement.velocityX = movement.speed;
    }

    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      movement.velocityY = movement.speed;
    } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      movement.velocityY = -movement.speed;
    }
  }
}
