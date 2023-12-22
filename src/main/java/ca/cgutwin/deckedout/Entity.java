package ca.cgutwin.deckedout;

public class Entity {
  private static int nextId = 0;
  private final int id;

  public Entity() {
    this.id = nextId++;
  }

  public int id() {
    return id;
  }
}
