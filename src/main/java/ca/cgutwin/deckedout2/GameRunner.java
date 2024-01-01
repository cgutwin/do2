/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2;

import ca.cgutwin.deckedout2.screens.TestScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameRunner extends Game {
  private SpriteBatch batch;

  @Override
  public void create() {
    batch = new SpriteBatch();

    setScreen(new TestScreen(this));
  }

  @Override
  public void dispose() {
    super.dispose();
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
  }

  // Getters and Setters
  public SpriteBatch batch() {
    return batch;
  }
}
