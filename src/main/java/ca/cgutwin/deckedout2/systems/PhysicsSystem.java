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

import ca.cgutwin.deckedout2.components.TransformationComponent;
import ca.cgutwin.deckedout2.components.b2d.BodyComponent;
import ca.cgutwin.deckedout2.utils.Coordinates;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSystem extends IteratingSystem {

  private static final float MAX_STEP_TIME = 1/60f;
  private static float accumulator = 0f;

  private final World world;
  private final List<Entity> bodiesQueue;
  private final ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
  private final ComponentMapper<TransformationComponent> transformationMapper = ComponentMapper.getFor(
          TransformationComponent.class);

  public PhysicsSystem(World world) {
    super(Family.all(BodyComponent.class, TransformationComponent.class).get());
    this.world       = world;
    this.bodiesQueue = new ArrayList<>();
  }

  @Override
  public void update(float dt) {
    super.update(dt);

    accumulator += dt;
    if (accumulator >= MAX_STEP_TIME) {
      world.step(MAX_STEP_TIME, 6, 2);
      accumulator -= MAX_STEP_TIME;

      for (Entity entity: bodiesQueue) {
        TransformationComponent transformationComponent = transformationMapper.get(entity);
        BodyComponent bodyComponent = bodyMapper.get(entity);

        Vector2 position = bodyComponent.body.getPosition();

        transformationComponent.position = new Coordinates(position).toBox2DCoordinates();
        transformationComponent.rotation = bodyComponent.body.getAngle()*MathUtils.radiansToDegrees;
      }
    }

    bodiesQueue.clear();
  }

  @Override
  protected void processEntity(Entity entity, float dt) {
    bodiesQueue.add(entity);
  }
}
