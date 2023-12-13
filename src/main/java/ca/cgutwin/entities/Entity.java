package ca.cgutwin.entities;

public class Entity implements IEntity {
  private static int nextId = 0;

  private final int id;

  public Entity() {
    this.id = nextId++;
  }

  @Override
  public int getId() {
    return id;
  }
}
