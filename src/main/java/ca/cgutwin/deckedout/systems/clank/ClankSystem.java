package ca.cgutwin.deckedout.systems.clank;

import ca.cgutwin.deckedout.events.EventDispatcher;
import ca.cgutwin.deckedout.systems.AbstractSystem;
import ca.cgutwin.deckedout.systems.clank.events.MaxClankReachedEvent;

public class ClankSystem extends AbstractSystem {
  public ClankSystem(EventDispatcher eventDispatcher) {
    super(eventDispatcher);
    setMaxValue(10);
  }

  @Override
  public void dispatchMaxValueEvent() {
    if (!maxEventDispatched) {
      eventDispatcher.dispatchEvent(new MaxClankReachedEvent("mc", "1"));
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
