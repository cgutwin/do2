package ca.cgutwin.deckedout.systems.treasure;

import ca.cgutwin.deckedout.events.EventDispatcher;
import ca.cgutwin.deckedout.systems.AbstractSystem;
import ca.cgutwin.deckedout.systems.treasure.events.MaxTreasureReachedEvent;
import ca.cgutwin.deckedout.systems.treasure.events.TreasureSpawnedEvent;
import ca.cgutwin.deckedout.systems.treasure.events.TreasureValueDecreaseEvent;

public class TreasureSystem extends AbstractSystem {
  public TreasureSystem(EventDispatcher eventDispatcher) {
    super(eventDispatcher);
    setMaxValue(10);
  }

  public void dispatchTreasureSpawnedEvent() {
    eventDispatcher.dispatchEvent(new TreasureSpawnedEvent("TreasureSpawned", "1"));
  }

  @Override
  public void dispatchMaxValueEvent() {
    if (!maxEventDispatched) {
      eventDispatcher.dispatchEvent(new MaxTreasureReachedEvent("MaxTreasureReachedEvent", "1"));
      maxEventDispatched = true;
    }
  }

  @Override
  public void reduceValue() {
    if (value > 0) {
      eventDispatcher.dispatchEvent(new TreasureValueDecreaseEvent("TreasureDecrease", ""));
      value--;
      maxEventDispatched = false;
    }
  }
}
