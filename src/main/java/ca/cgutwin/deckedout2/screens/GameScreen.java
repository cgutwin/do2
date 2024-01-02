/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.screens;

import ca.cgutwin.deckedout2.utils.CollisionObjectProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
  /* BOX2D */
  private final World world;
  private final Box2DDebugRenderer worldRenderer;
  private final TiledMap map;
  private final OrthogonalTiledMapRenderer mapRenderer;
  private final Viewport viewport;


  public GameScreen() {
    world         = new World(new Vector2(0, -10), true);
    worldRenderer = new Box2DDebugRenderer();

    map = new TmxMapLoader().load("crypt.tmx");
    int tileSize = ((TiledMapTileLayer) map.getLayers().get(0)).getTileWidth();

    mapRenderer = new OrthogonalTiledMapRenderer(map);

    // Parse Box2D collision maps from objects in the Tiled world.
    CollisionObjectProcessor collisionObjectProcessor = new CollisionObjectProcessor(world, tileSize);
    for (MapLayer layer: map.getLayers()) {
      if (layer instanceof TiledMapTileLayer tiledMapTileLayer) {
        collisionObjectProcessor.processLayer(tiledMapTileLayer);
      }
    }

    // This ensures that 30x20 tiles are always going to be visible, doesn't matter what the windows resolution is.
    viewport = new ExtendViewport(30*tileSize, 20*tileSize);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float dt) {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    world.step(Gdx.graphics.getDeltaTime(), 6, 2);

    viewport.apply();

    mapRenderer.setView((OrthographicCamera) viewport.getCamera());
    mapRenderer.render();

    worldRenderer.render(world, viewport.getCamera().combined);
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);
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
    mapRenderer.dispose();
    map.dispose();
  }
}
