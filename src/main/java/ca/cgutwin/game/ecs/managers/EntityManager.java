package ca.cgutwin.game.ecs.managers;

import ca.cgutwin.game.ecs.entities.Entity;

import java.util.*;

public class EntityManager {
  private final Map<Integer, Entity> entities;

  public EntityManager() {
    this.entities = new HashMap<>();
  }

  public Entity createEntity() {
    Entity entity = new Entity();
    entities.put(entity.getId(), entity);
    return entity;
  }

  public void destroyEntity(Entity entity) {
    entities.remove(entity.getId());
  }

  public Entity getEntityById(int id) {
    return entities.get(id);
  }

  public List<Entity> getAllEntities() {
    return new ArrayList<>(entities.values());
  }
}
