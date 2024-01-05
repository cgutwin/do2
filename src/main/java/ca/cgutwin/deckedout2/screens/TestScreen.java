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
import ca.cgutwin.deckedout2.components.PositionComponent;
import ca.cgutwin.deckedout2.entities.Player;
import ca.cgutwin.deckedout2.events.EventManager;
import ca.cgutwin.deckedout2.systems.ClankSystem;
import ca.cgutwin.deckedout2.systems.HazardSystem;
import ca.cgutwin.deckedout2.systems.InputSystem;
import ca.cgutwin.deckedout2.systems.PlayerMovementSystem;
import ca.cgutwin.deckedout2.systems.lighting.LightingSystemImpl;
import ca.cgutwin.deckedout2.systems.rendering.EntityRenderer;
import ca.cgutwin.deckedout2.systems.rendering.RenderingSystem;
import ca.cgutwin.deckedout2.systems.rendering.TextureRenderer;
import ca.cgutwin.deckedout2.ui.hud.HUD;
import ca.cgutwin.deckedout2.utils.CameraManager;
import ca.cgutwin.deckedout2.world.LevelManager;
import ca.cgutwin.deckedout2.world.LevelRenderer;
import ca.cgutwin.deckedout2.world.WorldContext;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TestScreen implements Screen {
  private final GameRunner game;
  private final CameraManager cameraManager;
  private final PooledEngine engine;
  private final Player player;
  private final EntityRenderer textureRenderer;
  private final HUD hud;
  private final LevelManager levelManager;
  private final LevelRenderer levelRenderer;
  private final EventManager eventManager;
  private final ClankSystem clankSystem;
  private final WorldContext worldContext;
  private final HazardSystem hazardSystem;

  public TestScreen(GameRunner game) {
    this.game    = game;
    worldContext = new WorldContext();

    levelManager = new LevelManager();

    // Add levels
    levelManager.addLevel("Crypt", "crypt.tmx");

    // Set the initial level
    levelManager.setCurrentLevel("Crypt");

    levelRenderer = new LevelRenderer(levelManager.currentLevel().tiledMap());

    cameraManager   = new CameraManager(800, 480);
    engine          = new PooledEngine();
    eventManager    = new EventManager();
    player          = new Player(engine);
    textureRenderer = new TextureRenderer();

    clankSystem  = new ClankSystem(worldContext);
    hazardSystem = new HazardSystem(worldContext);
    eventManager.subscribe(clankSystem);
    eventManager.subscribe(hazardSystem);

    engine.addSystem(new RenderingSystem(this.game.batch(), textureRenderer));
    engine.addSystem(new InputSystem());
    engine.addSystem(new PlayerMovementSystem());
    engine.addSystem(new LightingSystemImpl(this.game.batch()));

    TiledMapTileLayer layer = (TiledMapTileLayer) levelManager.currentLevel().tiledMap().getLayers().get("_Lighting");
    for (int x = 0; x < layer.getWidth(); x++) {
      for (int y = 0; y < layer.getHeight(); y++) {
        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
        if (cell != null) {
          Object props = cell.getTile().getProperties().get("colour");

          if (props != null) {
            Entity light = LightingSystemImpl.tilesToLightEntity(cell.getTile());
            light.add(new PositionComponent(x*layer.getTileWidth(), y*layer.getTileHeight()));
            engine.addEntity(light);
            System.out.println("added");
          }
        }
      }
    }
    // Initialize the HUD
    hud = new HUD(new ScreenViewport(), new Skin(Gdx.files.internal("skins/debug-skin/debug-skin.json")), worldContext);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float dt) {
    // Clear the screen
    Color color = Color.valueOf("090a14");
    Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    this.game.batch().setProjectionMatrix(cameraManager.getCamera().combined);
    updateCameraPosition();

    levelRenderer.render(cameraManager.getCamera());
    levelRenderer.update(dt);
    engine.update(dt);

    hud.stage().act(dt);
    hud.stage().draw();
    hud.update();
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
    if (levelRenderer != null) {
      levelRenderer.dispose();
    }

  }

  public void updateCameraPosition() {
    // Assume playerPosition is a Vector2 containing the player's current position
    Vector2 playerPosition = player.entity().getComponent(PositionComponent.class).position();

    // Center the camera on the player
    cameraManager.centerOn(playerPosition);
  }
}
