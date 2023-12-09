package ca.cgutwin.deckedout.game.ecs.entities.enemies;

import ca.cgutwin.deckedout.game.ecs.components.MovementComponent;
import ca.cgutwin.deckedout.game.ecs.components.Position;
import ca.cgutwin.deckedout.game.ecs.components.PositionComponent;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.managers.EntityManager;

public class Enemy extends Entity {
  protected final PositionComponent positionComponent;
  protected final MovementComponent movementComponent;
  protected Entity enemy;
  protected EntityManager entityManager = EntityManager.getInstance();

  public Enemy(Position spawnPosition) {
    this.enemy = entityManager.createEntity();

    this.positionComponent = new PositionComponent(spawnPosition);
    this.movementComponent = new MovementComponent(50);

    this.enemy.addComponent(positionComponent);
    this.enemy.addComponent(movementComponent);
  }

  public Entity getEnemy() {
    return enemy;
  }
}
