package ca.cgutwin.systems;

import ca.cgutwin.events.IEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventSystem implements ISystem {
  private final Map<Class<?>, List<IEventListener>> listeners = new HashMap<>();
  float timeSinceLastDispatch = 0f;

  public EventSystem() { }

  public void addListener(Class<?> eventType, IEventListener listener) {
    listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
  }

  public void dispatchEventEvery(float interval, Object event, float dT) {
    timeSinceLastDispatch += dT;
    if (timeSinceLastDispatch >= interval) {
      dispatchEvent(event);
      timeSinceLastDispatch = 0; // Reset the timer
    }
  }

  public void dispatchEvent(Object event) {
    List<IEventListener> eventListeners = listeners.get(event.getClass());
    if (eventListeners != null) {
      for (IEventListener listener: eventListeners) {
        listener.handle(event);
      }
    }
  }

  /**
   *
   */
  @Override
  public void init() {

  }

  @Override
  public void update(float dt) {

  }

  /**
   * @param dt
   */
  @Override
  public void render(float dt) {

  }

  /**
   *
   */
  @Override
  public void dispose() {

  }
}
