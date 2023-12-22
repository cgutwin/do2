package ca.cgutwin.deckedout.systems.treasure.events;

import ca.cgutwin.deckedout.events.Event;

public class TreasureSpawnedEvent extends Event {
  public TreasureSpawnedEvent(String type, String data) {
    super(type, data);
  }
}
