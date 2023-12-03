package ca.cgutwin.game.factories;

import ca.cgutwin.game.ecs.components.ComponentStub;
import ca.cgutwin.game.ecs.entities.Entity;
import ca.cgutwin.game.ecs.managers.EntityManager;

public class EntityFactory {
  private final EntityManager entityManager;

  public EntityFactory(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Entity createPlayer() {
    Entity player = entityManager.createEntity();
    // Add specific components for player
    player.addComponent(new ComponentStub("transform"));
    // ... other player-specific components ...
    return player;
  }
}
