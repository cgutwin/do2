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

import ca.cgutwin.deckedout2.components.b2d.BodyComponent;
import ca.cgutwin.deckedout2.utils.commands.Command;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class MovementCommand implements Command {
  private final Vector2 ds;

  public MovementCommand(int x, int y) {
    this.ds = new Vector2(x, y).nor();
  }

  @Override
  public void execute(Entity entity) {
    BodyComponent bodyComponent = entity.getComponent(BodyComponent.class);

    Vector2 linearVelocity = bodyComponent.body.getLinearVelocity();
    bodyComponent.body.setLinearVelocity(linearVelocity.add(ds));
  }
}
