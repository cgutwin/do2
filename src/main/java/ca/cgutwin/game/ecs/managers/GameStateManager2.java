package ca.cgutwin.game.ecs.managers;

import ca.cgutwin.game.factories.LevelFactory;
import ca.cgutwin.game.map.Level;

public class GameStateManager2 {
  private Level currentLevel;

  public void changeLevel(String levelName) {
    if (currentLevel != null) {
      currentLevel.dispose();
    }
    currentLevel = LevelFactory.createLevel(levelName);
    if (currentLevel != null) {
      currentLevel.load();
    }
  }

  public void update(float delta) {
    if (currentLevel != null) {
      currentLevel.update(delta);
    }
  }

  public void render() {
    if (currentLevel != null) {
      currentLevel.render();
    }
  }

  // Dispose and other methods...

  public void dispose() {
    if (currentLevel != null) {
      currentLevel.dispose();
    }
  }
}
