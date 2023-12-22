package ca.cgutwin;

import ca.cgutwin.deckedout.MainGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
  /*
   * Entities are usually lightweight identifiers, often without direct functionality or data. They serve as identifiers to group components.
   * In many ECS frameworks, an entity is typically represented by a unique ID:
   *
   * Components are pure data containers. Each component typically represents one aspect of an object's data or state.
   * A component can be a class with public fields (or private fields with getters and setters) but no logic:
   *
   * Systems contain the logic and operate on entities that have a specific set of components.
   * */
  public static void main(String[] arg) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("Decked Out");
    config.setWindowedMode(800, 480);
    config.useVsync(true);
    config.setForegroundFPS(60);
    new Lwjgl3Application(new MainGame(), config);
  }
}