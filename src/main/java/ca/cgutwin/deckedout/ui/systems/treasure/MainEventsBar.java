package ca.cgutwin.deckedout.ui.systems.treasure;

import ca.cgutwin.deckedout.events.Event;
import ca.cgutwin.deckedout.events.EventDispatcher;
import ca.cgutwin.deckedout.events.EventListenerInterface;
import ca.cgutwin.deckedout.systems.treasure.events.MaxTreasureReachedEvent;
import ca.cgutwin.deckedout.systems.treasure.events.TreasureSpawnedEvent;
import ca.cgutwin.deckedout.systems.treasure.events.TreasureValueDecreaseEvent;
import ca.cgutwin.deckedout.ui.systems.RenderableInterface;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainEventsBar implements RenderableInterface, EventListenerInterface {
  private final TextureRegion texture;
  private final EventDispatcher eventDispatcher;
  private final int x;
  private final int y;
  private int coins = 0;
  private boolean maxReached = false;

  public MainEventsBar(EventDispatcher eventDispatcher, TextureRegion texture, int x, int y) {
    this.eventDispatcher = eventDispatcher;
    this.x               = x;
    this.y               = y;
    this.texture         = texture;
  }

  public void registerListeners(String... listeners) {
    for (String listener: listeners) {
      eventDispatcher.registerListener(listener, this);
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    for (int i = 0; i < coins; i++) {
      sb.draw(texture, x + 50*i, y);
    }
  }

  @Override
  public void update(float deltaTime) {
    // Update logic here
  }

  /**
   *
   */
  @Override
  public void dispose() {

  }

  /**
   * @param event
   */
  @Override
  public void handle(Event event) {
    if (event instanceof TreasureSpawnedEvent && !maxReached) {
      coins++;
    }
    else if (event instanceof MaxTreasureReachedEvent) {
      maxReached = true;
    }
    else if (event instanceof TreasureValueDecreaseEvent) {
      if (coins > 0) {
        maxReached = false;
        coins--;
      }
    }
  }
}
