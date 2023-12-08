package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.events.GameEventListener;
import ca.cgutwin.deckedout.game.events.HazardGameEvent;

public class HazardSystem implements GameEventListener {
  private int hazardLevel = 0;

  @Override
  public void handle(Object event) {
    if (event instanceof HazardGameEvent) {
      hazardLevel += ((HazardGameEvent) event).getHazardValue();
      java.lang.System.out.println("Hazard: "+hazardLevel);
    }
  }
}
