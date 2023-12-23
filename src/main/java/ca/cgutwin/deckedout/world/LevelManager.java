package ca.cgutwin.deckedout.world;

import java.util.HashMap;
import java.util.Map;

public class LevelManager {
  // Define level identifiers
  public static final int LEVEL_ONE = 1;
  public static final int LEVEL_TWO = 2;
  public static final int LEVEL_THREE = 3;
  public static final int TRANSITION_LEVEL_ONE = 4;
  private final MapManager mapManager;
  // Map file paths for each level
  private final Map<Integer, String> levelMapPaths;
  private int currentLevel;

  public LevelManager(MapManager mapManager) {
    this.mapManager = mapManager;
    currentLevel    = LEVEL_ONE;

    levelMapPaths = new HashMap<>();
    levelMapPaths.put(LEVEL_ONE, "crypt.tmx");
    levelMapPaths.put(LEVEL_TWO, "crypt.tmx");
    levelMapPaths.put(LEVEL_THREE, "crypt.tmx");
    levelMapPaths.put(TRANSITION_LEVEL_ONE, "crypt.tmx");
  }

  public void loadLevel(int level) {
    if (levelMapPaths.containsKey(level)) {
      currentLevel = level;
      mapManager.loadMap(levelMapPaths.get(level));
    }
  }

  public int getCurrentLevel() {
    return currentLevel;
  }
}
