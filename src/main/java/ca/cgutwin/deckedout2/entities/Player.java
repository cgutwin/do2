/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.entities;

import ca.cgutwin.deckedout2.components.InputComponent;
import ca.cgutwin.deckedout2.components.PositionComponent;
import ca.cgutwin.deckedout2.utils.commands.movement.MovementCommandFactory;
import ca.cgutwin.deckedout2.utils.commands.movement.MovementEnum;
import ca.cgutwin.dyninet.Node;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Input;

public class Player {
  private final Entity entity;  // Ashley Entity
  private final Node node; // DIN Node

  // I don't like the drilling for the level, just to determine the collision. TODO: Make collision manager.
  public Player(PooledEngine engine) {
    // Initialize Ashley Entity
    entity = engine.createEntity();
    setupAshleyComponents();
    setupInput();

    // Initialize DIN Node
    node = new Node("PlayerNode");
    setupDINProperties();

    // Add the entity to the Ashley engine
    engine.addEntity(entity);
  }

  private void setupAshleyComponents() {
    // Add Ashley components to ashleyEntity
    //    TextureComponent texture = new TextureComponent(new Texture("img_1.png"));
    PositionComponent position = new PositionComponent(200, 100);

    //    entity.add(texture).add(position);
  }

  private void setupInput() {
    InputComponent inputComponent = new InputComponent();
    inputComponent.keyCommands.put(Input.Keys.W, MovementCommandFactory.newMovementCommand(MovementEnum.UP));
    inputComponent.keyCommands.put(Input.Keys.A, MovementCommandFactory.newMovementCommand(MovementEnum.LEFT));
    inputComponent.keyCommands.put(Input.Keys.S, MovementCommandFactory.newMovementCommand(MovementEnum.DOWN));
    inputComponent.keyCommands.put(Input.Keys.D, MovementCommandFactory.newMovementCommand(MovementEnum.RIGHT));
    entity.add(inputComponent);
  }

  private void setupDINProperties() {
    // Configure your DIN Node with specific properties or rules
  }

  // Getters and Setters

  public Entity entity() {
    return entity;
  }

  public Node node() {
    return node;
  }
}
