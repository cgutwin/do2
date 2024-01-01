/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.utils.commands.movement;

import ca.cgutwin.deckedout2.components.PositionComponent;
import ca.cgutwin.deckedout2.events.ClankEvent;
import ca.cgutwin.deckedout2.events.EventManager;
import ca.cgutwin.deckedout2.utils.commands.Command;
import ca.cgutwin.deckedout2.world.Level;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class MovementCommand implements Command {
  private final Vector2 ds;
  private final Level level;

  public MovementCommand(Vector2 ds, Level level) {
    this.level = level;
    this.ds    = ds;
  }

  public MovementCommand(int x, int y, Level level) {
    this.level = level;
    this.ds    = new Vector2(x, y);
  }

  @Override
  public void execute(Entity entity) {
    PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
    Vector2 newPosition = new Vector2(positionComponent.position());

    newPosition.add(ds);

    if (level.isTileWalkable(newPosition)) {
      EventManager.publishEvery(5, new ClankEvent());
      entity.getComponent(PositionComponent.class).move(ds);
    }
  }
}
