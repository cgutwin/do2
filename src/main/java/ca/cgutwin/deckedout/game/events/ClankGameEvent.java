package ca.cgutwin.deckedout.game.events;

public class ClankGameEvent implements GameEvent {
  private final int clankValue;

  public ClankGameEvent(int clankValue) {
    this.clankValue = clankValue;
  }

  public int getClankValue() {
    return clankValue;
  }
}
