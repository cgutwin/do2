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
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;

import java.util.Map.Entry;

public class InputSystem extends EntitySystem {
  private final ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);
  private ImmutableArray<Entity> entities;

  public InputSystem() {
    // Define the family of entities this system processes
  }

  @Override
  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.all(InputComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    for (Entity entity: entities) {
      InputComponent input = inputMapper.get(entity);
      for (Entry<Integer, Command> entry: input.keyCommands.entrySet()) {
        if (Gdx.input.isKeyPressed(entry.getKey())) {
          entry.getValue().execute(entity);
        }
      }
    }
  }
}
