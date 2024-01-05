/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.systems;

import ca.cgutwin.deckedout2.components.TextureComponent;
import ca.cgutwin.deckedout2.components.TransformationComponent;
import ca.cgutwin.deckedout2.utils.Coordinates;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderingSystem extends EntitySystem {
  private final ComponentMapper<TextureComponent> textureMapper;
  private final ComponentMapper<TransformationComponent> transformationMapper;
  private final SpriteBatch batch;
  private ImmutableArray<Entity> entities;

  public RenderingSystem(SpriteBatch batch) {
    this.batch                = batch;
    this.textureMapper        = ComponentMapper.getFor(TextureComponent.class);
    this.transformationMapper = ComponentMapper.getFor(TransformationComponent.class);
  }

  @Override
  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.all(TransformationComponent.class, TextureComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    for (Entity entity: entities) {
      TransformationComponent transformationComponent = transformationMapper.get(entity);
      TextureComponent textureComponent = textureMapper.get(entity);
      Coordinates coordinates = transformationComponent.position.toLibGDXCoordinates();

      batch.begin();
      batch.draw(textureComponent.texture, coordinates.x(), coordinates.y());
      batch.end();
    }
  }
}
