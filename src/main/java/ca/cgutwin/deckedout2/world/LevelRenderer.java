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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LevelRenderer {
  private final TiledMap tiledMap;
  private final MapProperties props;
  private final OrthogonalTiledMapRenderer mapRenderer;

  //  Lighting Rendering
  private final Texture pixel;
  private final SpriteBatch lightbatch;
  private final FrameBuffer lightingFrameBuffer;
  private final List<TiledMapTile> lightingTiles;
  private final int lightingTickSpeed = 10;
  private int currentLightingCoordinate = 0;
  private TiledMapTileLayer lightingLayer;

  public LevelRenderer(TiledMap tiledMap) {

    /* Map setup */
    this.tiledMap    = tiledMap;
    props            = this.tiledMap.getProperties();
    this.mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f);

    /* LIGHTING */
    pixel = generatePixel(1, 1, Color.WHITE);

    // Light Batching
    lightbatch = new SpriteBatch();
    lightbatch.disableBlending();

    // Light FrameBuffer
    lightingFrameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, props.get("width", Integer.class),
                                          props.get("height", Integer.class), false);
    lightingTiles       = generateLightingTiles(tiledMap.getTileSets().getTileSet("lighting"));
    setupLightingLayer();
  }

  private Texture generatePixel(int width, int height, Color color) {
    Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
    pixmap.setColor(color);
    pixmap.fill();
    return new Texture(pixmap);
  }

  private List<TiledMapTile> generateLightingTiles(TiledMapTileSet tileset) {
    ArrayList<TiledMapTile> tiles = new ArrayList<>();
    tileset.iterator().forEachRemaining(tiles::add);
    tiles.sort(Comparator.comparing(o -> o.getProperties().get("index", Integer.class)));
    return tiles.reversed();
  }

  private void setupLightingLayer() {
    lightingLayer = new TiledMapTileLayer(props.get("width", Integer.class), props.get("height", Integer.class),
                                          props.get("tilewidth", Integer.class),
                                          props.get("tileheight", Integer.class));
    tiledMap.getLayers().add(lightingLayer);
  }

  public void update(float dt) {
    lightingFrameBuffer.begin();
    lightbatch.begin();

    for (int i = 0; i < lightingTickSpeed && currentLightingCoordinate < lightingLayer.getWidth()*lightingLayer.getHeight(); i++, currentLightingCoordinate++) {
      int x = currentLightingCoordinate/lightingLayer.getHeight();
      int invY = currentLightingCoordinate%lightingLayer.getHeight();
      int y = lightingLayer.getHeight() - 1 - invY;

      if (y <= 1 || ((TiledMapTileLayer) tiledMap.getLayers().get("ground")).getCell(x, y) != null) {
        setTileLighting(x, y);
      }
    }

    lightbatch.end();
    lightingFrameBuffer.end();
  }

  private void setTileLighting(int x, int y) {
    float average = calculateAverageLightingOfTile((TiledMapTileLayer) tiledMap.getLayers().get("ground"), x, y);

    lightbatch.setColor(new Color(0, 0, 0, average));
    lightbatch.draw(pixel, x, y);

    // since the average generates floats from 0.0 to 0.1 in steps of 0.1 (e.g. 0.1XXXX, 0.2XXXX, 0.3XXXX) we can clamp it and figure out the index via that
    int index = (int) (average*10);

    // create the cell with the appropriately tinted tile
    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
    cell.setTile(lightingTiles.get(index));
    lightingLayer.setCell(x, y, cell);
  }

  // calculates lighting average for this one specific tile according to https://gamedev.stackexchange.com/a/126165
  // calculate by how many tiles the current tile is surrounded. co-ords outside the map also count as tiles
  private float calculateAverageLightingOfTile(TiledMapTileLayer layer, int x, int y) {
    int averageSum = 0;
    for (int dY = y - 1; dY <= y + 1; dY++) {
      for (int dX = x - 1; dX <= x + 1; dX++) {
        if (dX == x && dY == y) // ignore when it's the current tile
        { continue; }

        boolean foundTile = false;
        if (dX < 0 || dY < 0 || dX >= layer.getWidth() || dY >= layer.getHeight()) {
          foundTile = true; // out of bounds
        }
        else if (layer.getCell(dX, dY) != null) { foundTile = true; }
        else if (y <= 1) { foundTile = true; }

        averageSum += foundTile ? 1 : 0;
      }
    }

    return averageSum/18f;
  }

  public void render(OrthographicCamera camera) {
    mapRenderer.setView(camera);
    mapRenderer.render();
  }

  public void dispose() {
    if (tiledMap != null) {
      tiledMap.dispose();
    }
    mapRenderer.dispose();
  }
}

