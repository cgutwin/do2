package ca.cgutwin.deckedout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComponentManager {
  // Map from Entity ID to another Map, which maps Component Types to Components
  private final Map<Integer, Map<Class<? extends Component>, Component>> componentsByEntity = new HashMap<>();
  private final Map<Class<? extends Component>, Set<Integer>> entitiesByComponentType = new HashMap<>();

  public void addComponent(Entity entity, Component component) {
    componentsByEntity.computeIfAbsent(entity.id(), k -> new HashMap<>()).put(component.getClass(), component);

    entitiesByComponentType.computeIfAbsent(component.getClass(), k -> new HashSet<>()).add(entity.id());
  }

  public <T extends Component> T getComponent(Entity entity, Class<T> componentClass) {
    Map<Class<? extends Component>, Component> components = componentsByEntity.get(entity.id());
    if (components != null) {
      return componentClass.cast(components.get(componentClass));
    }
    return null;
  }

  public <T extends Component> void removeComponent(Entity entity, Class<T> componentClass) {
    Map<Class<? extends Component>, Component> components = componentsByEntity.get(entity.id());
    if (components != null) {
      components.remove(componentClass);
    }

    Set<Integer> entities = entitiesByComponentType.get(componentClass);
    if (entities != null) {
      entities.remove(entity.id());
    }
  }

  public void removeAllComponents(Entity entity) {
    componentsByEntity.remove(entity.id());

    componentsByEntity.get(entity.id()).forEach((componentType, component) -> {
      Set<Integer> entities = entitiesByComponentType.get(componentType);
      if (entities != null) {
        entities.remove(entity.id());
      }
    });
  }

  public Set<Integer> getEntitiesWithComponent(Class<? extends Component> componentType) {
    return entitiesByComponentType.getOrDefault(componentType, new HashSet<>());
  }
}
