/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.ui.hud.elements;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class StatusBar {
  private final ProgressBar progressBar;

  public StatusBar(Skin skin, String styleName) {
    progressBar = new ProgressBar(0, 100, 1, false, skin, styleName);
  }

  // Getters and Setters

  public void setValue(float value) {
    progressBar.setValue(value);
  }

  public ProgressBar progressBar() {
    return progressBar;
  }

  // Additional methods for customization
}
