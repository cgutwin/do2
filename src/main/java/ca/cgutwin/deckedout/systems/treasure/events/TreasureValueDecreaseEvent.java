package ca.cgutwin.deckedout.systems.treasure.events;

import ca.cgutwin.deckedout.events.Event;

public class TreasureValueDecreaseEvent extends Event {
  public TreasureValueDecreaseEvent(String type, String data) {
    super(type, data);
  }
}
