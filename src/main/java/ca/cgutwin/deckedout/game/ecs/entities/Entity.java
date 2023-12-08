package ca.cgutwin.deckedout.game.ecs.entities;

import ca.cgutwin.deckedout.game.ecs.components.Component;

import java.util.HashMap;
import java.util.Map;

public class Entity {
  private static int idCounter = 0;
  private final int id;
  private final Map<Class<? extends Component>, Component> components;

  public Entity() {
    this.components = new HashMap<>();
    this.id         = idCounter++;
  }

  public void addComponent(Component component) {
    components.put(component.getClass(), component);
  }

  public void removeComponent(Component component) {
    components.remove(component.getClass());
  }

  public <T extends Component> T getComponent(Class<T> componentClass) {
    return componentClass.cast(components.get(componentClass));
  }

  public boolean hasComponent(Class<? extends Component> componentClass) {
    return components.containsKey(componentClass);
  }

  public int getId() {
    return id;
  }
}

