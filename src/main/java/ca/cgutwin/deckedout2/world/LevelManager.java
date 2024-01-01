/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.world;

import java.util.HashMap;
import java.util.Map;

public class LevelManager {
  private final Map<String, Level> levels;
  private Level currentLevel;

  public LevelManager() {
    levels = new HashMap<>();
  }

  public void addLevel(String levelName, String mapFilePath) {
    levels.put(levelName, new Level(levelName, mapFilePath));
  }

  public Level currentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(String levelName) {
    currentLevel = levels.get(levelName);
    // Load level-specific data, set up the environment, etc.
  }

  // Methods to handle transitions between levels, unload levels, etc.
}
