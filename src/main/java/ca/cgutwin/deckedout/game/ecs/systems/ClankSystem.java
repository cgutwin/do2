package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.events.ClankGameEvent;
import ca.cgutwin.deckedout.game.events.GameEventListener;
import ca.cgutwin.deckedout.game.events.HazardGameEvent;

public class ClankSystem implements GameEventListener {
  private int clankLevel = 0;
  private EventSystem eventSystem = EventSystem.getInstance();


  @Override
  public void handle(Object event) {
    if (event instanceof ClankGameEvent) {
      clankLevel += ((ClankGameEvent) event).getClankValue();

      if (this.clankLevel%5 == 0) {
        eventSystem.dispatchEvent(new HazardGameEvent(1));
      }

      java.lang.System.out.println(clankLevel);
    }
  }
}
