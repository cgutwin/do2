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

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;
import com.badlogic.gdx.math.Vector2;

public class Level {
  private final TiledMap tiledMap;
  private final String levelName;

  public Level(String levelName, String mapFilePath) {
    this.levelName = levelName;

    Parameters params = new Parameters();

    params.textureMinFilter = TextureFilter.Nearest; //Linear;

    params.textureMagFilter = TextureFilter.Nearest; //Linear;Nearest

    this.tiledMap = new TmxMapLoader().load(mapFilePath, params);
  }


  public boolean isTileWalkable(Vector2 location) {
    return isTileWalkable(location.x, location.y);
  }

  public boolean isTileWalkable(float x, float y) {
    TiledMapTileLayer collisionLayer = (TiledMapTileLayer) tiledMap.getLayers().get("ground");
    if (collisionLayer == null) { return true; }

    // Convert world coordinates (e.g., pixels) to tile coordinates
    int tileWidth = collisionLayer.getTileWidth();
    int tileHeight = collisionLayer.getTileHeight();

    float tileX = x/tileWidth;
    float tileY = y/tileHeight;

    TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (tileX), (int) (tileY));

    return cell != null;
  }

  public TiledMap tiledMap() {
    return tiledMap;
  }

  public String levelName() {
    return levelName;
  }

  // Additional level-specific methods (e.g., handling level-specific events)
}
