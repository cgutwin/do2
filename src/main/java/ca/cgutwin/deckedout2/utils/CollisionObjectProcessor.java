/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 *
 * This code is adapted from https://github.com/lyze237-examples/LibgdxTiledBox2DExample under the Apache 2.0 License.
 * A copy of which is provided in the docs folder of this project.
 */

package ca.cgutwin.deckedout2.utils;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.World;

public class CollisionObjectProcessor {
  private final MapBodyBuilder mapBodyBuilder;

  /**
   * Constructor for CollisionObjectProcessor.
   *
   * @param world    The Box2D world where bodies will be created.
   * @param tileSize The size of each tile in the world, used for scaling.
   */
  public CollisionObjectProcessor(World world, float tileSize) {
    this.mapBodyBuilder = new MapBodyBuilder(world, tileSize);
  }

  /**
   * Processes each tile in a TiledMapTileLayer to create Box2D bodies.
   *
   * @param layer The TiledMapTileLayer to process.
   */
  public void processLayer(TiledMapTileLayer layer) {
    for (int x = 0; x < layer.getWidth(); x++) {
      for (int y = 0; y < layer.getHeight(); y++) {
        this.createBodyForCell(layer.getCell(x, y), x, y);
      }
    }
  }

  /**
   * Delegates the creation of a Box2D body for a specific cell to the MapBodyBuilder.
   *
   * @param cell The cell from the Tiled map layer.
   * @param x    The x-coordinate of the cell.
   * @param y    The y-coordinate of the cell.
   */
  private void createBodyForCell(TiledMapTileLayer.Cell cell, int x, int y) {
    if (cell != null) {
      mapBodyBuilder.createBody(cell, x, y);
    }
  }
}
