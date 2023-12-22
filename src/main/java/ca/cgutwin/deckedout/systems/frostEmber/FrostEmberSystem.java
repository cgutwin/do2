package ca.cgutwin.deckedout.systems.frostEmber;

import ca.cgutwin.deckedout.events.EventDispatcher;
import ca.cgutwin.deckedout.systems.AbstractSystem;
import ca.cgutwin.deckedout.systems.frostEmber.events.FrostEmberSpawnedEvent;
import ca.cgutwin.deckedout.systems.frostEmber.events.FrostEmbersDecreaseEvent;
import ca.cgutwin.deckedout.systems.frostEmber.events.MaxFrostEmbersReachedEvent;

public class FrostEmberSystem extends AbstractSystem {
  public FrostEmberSystem(EventDispatcher eventDispatcher) {
    super(eventDispatcher);
    setMaxValue(10);
  }

  public void dispatchSpawnFrostEmberEvent() {
    eventDispatcher.dispatchEvent(new FrostEmberSpawnedEvent("FrostEmberSpawned", "1"));
  }

  @Override
  public void dispatchMaxValueEvent() {
    if (!maxEventDispatched) {
      eventDispatcher.dispatchEvent(new MaxFrostEmbersReachedEvent("MaxFrostEmbersReached", "1"));
      maxEventDispatched = true;
    }
  }

  @Override
  public void reduceValue() {
    if (value > 0) {
      eventDispatcher.dispatchEvent(new FrostEmbersDecreaseEvent("FrostEmbersDecrease", ""));
      value--;
      maxEventDispatched = false;
    }
  }
}
