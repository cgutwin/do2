package ca.cgutwin.deckedout2.util;

public class DebugSystem
{
  private static DebugSystem instance;
  public boolean debugging;

  private DebugSystem() {
    this.debugging = true;
  }

  public static DebugSystem getInstance() {
    if (instance == null) {
      instance = new DebugSystem();
    }
    return instance;
  }

  public boolean setDebugging() {
    return debugging;
  }

  public void sout(String string) {
    if (debugging) { System.out.println(string); }
  }
}
