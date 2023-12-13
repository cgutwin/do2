package ca.cgutwin.components;

import ca.cgutwin.entities.Entity;

import java.util.*;

public class ComponentManager {
  private final Map<Class<? extends IComponent>, Map<Integer, IComponent>> componentsByClass = new HashMap<>();

  public void addComponent(Entity entity, IComponent component) {
    componentsByClass.computeIfAbsent(component.getClass(), k -> new HashMap<>()).put(entity.getId(), component);
  }

  public <T extends IComponent> T getComponent(Entity entity, Class<T> componentClass) {
    return componentClass.cast(componentsByClass.getOrDefault(componentClass, new HashMap<>()).get(entity.getId()));
  }

  public <T extends IComponent> Set<Integer> getEntitiesWithComponent(Class<T> componentClass) {
    return componentsByClass.getOrDefault(componentClass, new HashMap<>()).keySet();
  }

  public List<IComponent> getAllComponents(Entity entity) {
    List<IComponent> components = new ArrayList<>();

    for (Map<Integer, IComponent> compMap: componentsByClass.values()) {
      if (compMap.containsKey(entity.getId())) {
        components.add(compMap.get(entity.getId()));
      }
    }

    return components;
  }
}