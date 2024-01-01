/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.systems;

import ca.cgutwin.deckedout2.components.ClankComponent;
import ca.cgutwin.deckedout2.components.MovementComponent;
import ca.cgutwin.deckedout2.components.PlayerComponent;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

public class PlayerMovementSystem extends EntitySystem {
  private final ComponentMapper<MovementComponent> movementMapper;
  private ImmutableArray<Entity> players;

  public PlayerMovementSystem() {
    movementMapper = ComponentMapper.getFor(MovementComponent.class);
  }

  @Override
  public void addedToEngine(Engine engine) {
    players = engine.getEntitiesFor(Family.all(PlayerComponent.class, MovementComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    for (Entity player: players) {
      ClankComponent clank = players.first().getComponent(ClankComponent.class);
      MovementComponent movement = movementMapper.get(player);
      clank.increaseClank(calculateClankIncrease());
    }
  }

  private float calculateClankIncrease() {
    // Implement logic to calculate how much the hazard should increase
    return 1;
  }
}
