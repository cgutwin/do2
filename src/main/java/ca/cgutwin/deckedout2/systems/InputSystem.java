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

import ca.cgutwin.deckedout2.components.InputComponent;
import ca.cgutwin.deckedout2.utils.commands.Command;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class InputSystem extends IteratingSystem {
  private final ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);
  private final List<Entity> entitiesQueue;

  public InputSystem() {
    super(Family.all(InputComponent.class).get());
    this.entitiesQueue = new ArrayList<>();
  }

  @Override
  public void update(float dt) {
    super.update(dt);

    for (Entity entity: entitiesQueue) {
      InputComponent input = inputMapper.get(entity);
      for (Entry<Integer, Command> entry: input.keyCommands.entrySet()) {
        if (Gdx.input.isKeyPressed(entry.getKey())) {
          entry.getValue().execute(entity);
        }
      }
    }
  }

  @Override
  protected void processEntity(Entity entity, float v) {
    entitiesQueue.add(entity);
  }
}
