/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.systems.lighting;

import ca.cgutwin.deckedout2.components.LightComponent;
import ca.cgutwin.deckedout2.components.PositionComponent;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

public class LightingSystemImpl extends EntitySystem {
  private final ComponentMapper<LightComponent> lightingMapper = ComponentMapper.getFor(LightComponent.class);
  private final SpriteBatch batch;
  private ImmutableArray<Entity> entities;

  public LightingSystemImpl(SpriteBatch batch) {

    this.batch = batch;
  }

  public static Entity tilesToLightEntity(TiledMapTile tile) {
    MapProperties props = tile.getProperties();

    LightComponent lightComponent = new LightComponent(props.get("colour", Color.class), 0.75F, 80);
    if (props.containsKey("lightOffsetX") || props.containsKey("lightOffsetY")) {
      float offsetX = props.get("lightOffsetX", Float.class);
      float offsetY = props.get("lightOffsetY", Float.class);

      lightComponent.lightOffsetX = offsetX;
      lightComponent.lightOffsetY = offsetY;
    }

    return new Entity().add(lightComponent);
  }

  @Override
  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.all(LightComponent.class, PositionComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    for (Entity entity: entities) {
      LightComponent lightComponent = entity.getComponent(LightComponent.class);
      PositionComponent positionComponent = entity.getComponent(PositionComponent.class);

      batch.begin();
      batch.setColor(lightComponent.color.r, lightComponent.color.g, lightComponent.color.b, lightComponent.intensity);
      batch.draw(new Texture(Gdx.files.internal("light-1.png")),
                 (positionComponent.position().x + lightComponent.lightOffsetX) - lightComponent.size/2f + 8,
                 (positionComponent.position().y + lightComponent.lightOffsetY) - lightComponent.size/2f + 8,
                 lightComponent.size, lightComponent.size);
      batch.setColor(Color.WHITE);
      batch.end();
    }
  }
}
