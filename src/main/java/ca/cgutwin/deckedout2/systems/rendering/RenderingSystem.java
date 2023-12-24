/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.systems.rendering;

import ca.cgutwin.deckedout2.components.PositionComponent;
import ca.cgutwin.deckedout2.components.TextureComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderingSystem extends EntitySystem {
  private final SpriteBatch spriteBatch;
  private final EntityRenderer renderer;
  private ImmutableArray<Entity> entities;

  public RenderingSystem(SpriteBatch spriteBatch, EntityRenderer renderer) {
    this.spriteBatch = spriteBatch;
    this.renderer    = renderer;
  }

  @Override
  public void addedToEngine(com.badlogic.ashley.core.Engine engine) {
    entities = engine.getEntitiesFor(Family.all(TextureComponent.class, PositionComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    spriteBatch.begin();
    for (Entity entity: entities) {
      renderer.render(entity, spriteBatch);
    }
    spriteBatch.end();
  }
}