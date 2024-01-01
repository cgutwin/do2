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
import ca.cgutwin.deckedout2.world.WorldContext;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
  private final Stage stage;
  private final StatusBar clankBar;
  private final StatusBar hazardBar;
  private final WorldContext context;

  public HUD(Viewport viewport, Skin skin, WorldContext context) {
    this.context = context;

    stage = new Stage(viewport);

    // Initialize UI components
    clankBar = new StatusBar(skin, "default-horizontal");

    stage.addActor(clankBar.progressBar());
    hazardBar = new StatusBar(skin, "default-horizontal");
  }

  public void update() {
    clankBar.setValue(context.clank());
  }

  // Getters and Setters.

  public Stage stage() {
    return stage;
  }

  public StatusBar clankBar() {
    return clankBar;
  }
}
