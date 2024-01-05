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

import ca.cgutwin.deckedout2.components.PlayerComponent;
import ca.cgutwin.deckedout2.components.TypeComponent;
import ca.cgutwin.deckedout2.components.b2d.CollisionComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class CollisionSystem extends IteratingSystem {
  ComponentMapper<CollisionComponent> collisionMapper;
  ComponentMapper<PlayerComponent> playerMapper;

  public CollisionSystem() {
    // only need to worry about player collisions
    super(Family.all(CollisionComponent.class, PlayerComponent.class).get());

    collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    playerMapper    = ComponentMapper.getFor(PlayerComponent.class);
  }

  @Override
  protected void processEntity(Entity entity, float v) {
    CollisionComponent collisionComponent = collisionMapper.get(entity);

    Entity collidedEntity = collisionComponent.collisionEntity;

    if (collidedEntity != null) {
      TypeComponent entityType = collidedEntity.getComponent(TypeComponent.class);

      if (entityType != null) {
        switch (entityType.type) {
          case PLAYER -> System.out.println("player hit");
          case ENEMY -> System.out.println("enemy hit");
          case SCENERY -> System.out.println("scenery hit");
          case OTHER -> System.out.println("other hit");
        }

        // The collision has been handled so reset the component.
        collisionComponent.collisionEntity = null;
      }

    }
  }
}
