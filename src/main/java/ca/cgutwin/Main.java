package ca.cgutwin;

import ca.cgutwin.core.GameApplication;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
  public static void main (String[] arg) {
    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("game");
    config.setWindowedMode(800, 480);
    config.useVsync(true);
    config.setForegroundFPS(60);
    new Lwjgl3Application(new GameApplication(), config);
  }
}