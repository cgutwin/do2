package ca.cgutwin.game.factories;

import ca.cgutwin.game.map.Level;
import ca.cgutwin.game.map.Level1;

public class LevelFactory {
  public static Level createLevel(String levelName) {
    switch (levelName) {
      case "Level1" -> {
        return new Level1();
      }
      default -> {
        return null;
      }
    }
  }
}
