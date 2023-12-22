package ca.cgutwin.deckedout.systems;

import ca.cgutwin.deckedout.events.EventDispatcher;

public abstract class AbstractSystem {
  protected final EventDispatcher eventDispatcher;
  protected int maxValue;
  protected int value;
  protected boolean maxEventDispatched = false;

  public AbstractSystem(EventDispatcher eventDispatcher) {
    this.eventDispatcher = eventDispatcher;
  }

  public int maxValue() {
    return maxValue;
  }

  public int value() {
    return value;
  }

  public void incrementValueIfAble() {
    if (value >= maxValue) {
      this.dispatchMaxValueEvent();
    }
    else {
      this.value++;
    }
  }

  public abstract void dispatchMaxValueEvent();

  public abstract void reduceValue();

  public void setMaxValue(int maxValue) {
    this.maxValue = maxValue;
  }
}
