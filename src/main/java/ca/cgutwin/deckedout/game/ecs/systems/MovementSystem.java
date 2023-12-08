package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.ecs.components.MovementComponent;
import ca.cgutwin.deckedout.game.ecs.components.PositionComponent;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.managers.EntityManager;
import ca.cgutwin.deckedout.game.ecs.managers.MapManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovementSystem implements System {
  private final SpriteBatch sb;
  private final EntityManager entityManager = EntityManager.getInstance();
  private final MapManager mapManager;

  public MovementSystem(SpriteBatch spriteBatch, MapManager mapManager) {
    this.sb = spriteBatch;
    this.mapManager = mapManager;
  }

  @Override
  public void update(float dT) {
    sb.begin();
    for (Entity entity: entityManager.getAllEntities()) {
      if (entity.hasComponent(MovementComponent.class) && entity.hasComponent(PositionComponent.class)) {
        updateEntity(entity, dT);
      }
    }
    sb.end();
  }

  private void updateEntity(Entity entity, float dT) {
    MovementComponent movement = entity.getComponent(MovementComponent.class);
    PositionComponent position = entity.getComponent(PositionComponent.class);

    float newX = position.x + movement.velocityX * dT;
    float newY = position.y + movement.velocityY * dT;

    if (canMoveTo(newX, newY, 8, 8)) {
      position.x = newX;
      position.y = newY;
    }
  }

  public boolean canMoveTo(float x, float y, float playerWidth, float playerHeight) {
    // Calculate the corners of the player's bounding box
    float left = x - playerWidth / 2;
    float right = x + playerWidth / 2;
    float bottom = y - playerHeight / 2;
    float top = y + playerHeight / 2;

    // Check if any corner is in a non-walkable tile
    return mapManager.isTileWalkable(left, bottom) &&
            mapManager.isTileWalkable(right, bottom) &&
            mapManager.isTileWalkable(left, top) &&
            mapManager.isTileWalkable(right, top);
  }
}
