package ca.cgutwin.deckedout.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcher {
  private final Map<String, List<EventListenerInterface>> listeners = new HashMap<>();

  public void registerListener(String eventType, EventListenerInterface listener) {
    this.listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
  }

  public void unregisterListener(String eventType, EventListenerInterface listener) {
    this.listeners.getOrDefault(eventType, new ArrayList<>()).remove(listener);
  }

  public void dispatchEvent(Event event) {
    List<EventListenerInterface> eventListeners = listeners.getOrDefault(event.getType(), new ArrayList<>());
    for (EventListenerInterface listener: eventListeners) {
      listener.handle(event);
    }
  }
}