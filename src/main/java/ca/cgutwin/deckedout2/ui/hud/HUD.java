/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.ui.hud;

import ca.cgutwin.deckedout2.ui.hud.elements.StatusBar;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
  private final Stage stage;
  private final StatusBar healthBar;

  public HUD(Viewport viewport, Skin skin) {
    stage = new Stage(viewport);

    // Initialize UI components
    healthBar = new StatusBar(skin, "default-horizontal");

    stage.addActor(healthBar.progressBar());
  }

  // Getters and Setters.

  public Stage stage() {
    return stage;
  }

  public StatusBar healthBar() {
    return healthBar;
  }
}
