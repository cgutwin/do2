package ca.cgutwin.entities;

import java.util.HashSet;
import java.util.Set;

public class EntityManager {
  private final Set<Entity> entities = new HashSet<>();
  private Entity player;

  public EntityManager() {
  }

  public Entity getPlayer() {
    return player;
  }

  public Set<Entity> getEntities() {
    return entities;
  }

  public Entity createPlayer() {
    this.player = createEntity();
    return this.player;
  }

  public Entity createEntity() {
    Entity entity = new Entity();
    entities.add(entity);
    return entity;
  }

  public void destroyEntity(Entity entity) {
    entities.remove(entity);
  }
}