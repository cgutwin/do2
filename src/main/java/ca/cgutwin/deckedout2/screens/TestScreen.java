/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.screens;

import ca.cgutwin.deckedout2.GameRunner;
import ca.cgutwin.deckedout2.entities.Player;
import ca.cgutwin.deckedout2.systems.rendering.EntityRenderer;
import ca.cgutwin.deckedout2.systems.rendering.RenderingSystem;
import ca.cgutwin.deckedout2.systems.rendering.TextureRenderer;
import ca.cgutwin.deckedout2.ui.hud.HUD;
import ca.cgutwin.deckedout2.utils.CameraManager;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TestScreen implements Screen {
  private final GameRunner game;
  private final CameraManager cameraManager;
  private final PooledEngine engine;
  private final Player player;
  private final EntityRenderer textureRenderer;
  private final HUD hud;


  public TestScreen(GameRunner game) {
    this.game = game;

    cameraManager   = new CameraManager(800, 480);
    engine          = new PooledEngine();
    player          = new Player(engine);
    textureRenderer = new TextureRenderer();

    engine.addSystem(new RenderingSystem(this.game.batch(), textureRenderer));

    // Initialize the HUD
    hud = new HUD(new ScreenViewport(), new Skin(Gdx.files.internal("skins/debug-skin/debug-skin.json")));
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float dt) {
    engine.update(dt);

    hud.stage().act(dt);
    hud.stage().draw();
  }

  @Override
  public void resize(int width, int height) {
    cameraManager.resize(width, height);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
  }
}
