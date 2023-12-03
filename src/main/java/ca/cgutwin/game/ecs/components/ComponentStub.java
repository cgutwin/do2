package ca.cgutwin.game.ecs.components;

public class ComponentStub implements Component {
  private final String name;

  public ComponentStub(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
