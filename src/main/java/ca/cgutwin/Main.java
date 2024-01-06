package ca.cgutwin;

import ca.cgutwin.deckedout2.GameRunner;
import ca.cgutwin.deckedout2.util.DebugSystem;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main
{
  public static void main(String[] arg) {
    DebugSystem.getInstance().sout("debugging output enabled\n*----------*");

    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
    config.setTitle("game");
    config.setWindowedMode(800, 480);
    config.useVsync(true);
    config.setForegroundFPS(60);
    new Lwjgl3Application(new GameRunner(), config);
  }
}