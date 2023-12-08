package ca.cgutwin.deckedout.game.events;

public class HazardGameEvent implements GameEvent {
  private final int hazardValue;

  public HazardGameEvent(int hazardValue) {
    this.hazardValue = hazardValue;
  }

  public int getHazardValue() {
    return hazardValue;
  }
}
