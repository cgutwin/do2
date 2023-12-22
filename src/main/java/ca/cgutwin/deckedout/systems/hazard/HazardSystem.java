package ca.cgutwin.deckedout.systems.hazard;

import ca.cgutwin.deckedout.events.EventDispatcher;
import ca.cgutwin.deckedout.systems.AbstractSystem;
import ca.cgutwin.deckedout.systems.hazard.events.MaxHazardReachedEvent;

public class HazardSystem extends AbstractSystem {
  public HazardSystem(EventDispatcher eventDispatcher) {
    super(eventDispatcher);
    setMaxValue(10);
  }

  @Override
  public void dispatchMaxValueEvent() {
    if (!maxEventDispatched) {
      eventDispatcher.dispatchEvent(new MaxHazardReachedEvent("mc", "1"));
      maxEventDispatched = true;
    }
  }

  /**
   *
   */
  @Override
  public void reduceValue() {

  }
}
