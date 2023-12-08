package ca.cgutwin.deckedout.game.ecs.managers;

import ca.cgutwin.deckedout.game.ecs.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManager {
  private static EntityManager instance;
  private final Map<Integer, Entity> entities;

  private EntityManager() {
    this.entities = new HashMap<>();
  }

  public static EntityManager getInstance() {
    if (instance == null) {
      instance = new EntityManager();
    }
    return instance;
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
