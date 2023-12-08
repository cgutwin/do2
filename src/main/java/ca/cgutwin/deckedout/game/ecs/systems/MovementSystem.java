package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.ecs.components.MovementComponent;
import ca.cgutwin.deckedout.game.ecs.components.PositionComponent;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.managers.EntityManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovementSystem implements System {
  private final SpriteBatch sb;
  private final EntityManager entityManager = EntityManager.getInstance();

  public MovementSystem(SpriteBatch spriteBatch) {
    this.sb = spriteBatch;
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

    // Update position based on movement data
    position.x += movement.velocityX*dT;
    position.y += movement.velocityY*dT;
  }
}
