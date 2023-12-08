package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.events.GameEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventSystem implements System {
  private static EventSystem instance;
  private final Map<Class<?>, List<GameEventListener>> listeners = new HashMap<>();
  float timeSinceLastDispatch = 0f;

  public static EventSystem getInstance() {
    if (instance == null) {
      instance = new EventSystem();
    }
    return instance;
  }

  private EventSystem() {}

  public void addListener(Class<?> eventType, GameEventListener listener) {
    listeners.computeIfAbsent(eventType, k->new ArrayList<>()).add(listener);
  }

  public void dispatchEvent(Object event) {
    List<GameEventListener> eventListeners = listeners.get(event.getClass());
    if (eventListeners != null) {
      for (GameEventListener listener: eventListeners) {
        listener.handle(event);
      }
    }
  }

  public void dispatchEventEvery(float interval, Object event, float dT) {
    timeSinceLastDispatch += dT;
    if (timeSinceLastDispatch >= interval) {
      dispatchEvent(event);
      timeSinceLastDispatch = 0; // Reset the timer
    }
  }

  @Override
  public void update(float deltaTime) {

  }
}
