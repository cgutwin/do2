package ca.cgutwin.deckedout.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class WorldManager {
  private final World world;

  public WorldManager() {
    world = new World(new Vector2(0, 0), true);
  }

  public World world() {
    return world;
  }

  public void update(float dt) {
    world.step(dt, 6, 2); // Update the world
  }

  public void dispose() {
    world.dispose();
  }
}
