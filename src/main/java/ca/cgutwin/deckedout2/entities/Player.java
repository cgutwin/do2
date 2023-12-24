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

import ca.cgutwin.deckedout2.components.PositionComponent;
import ca.cgutwin.deckedout2.components.TextureComponent;
import ca.cgutwin.dyninet.Node;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;

public class Player {
  private final Entity entity;  // Ashley Entity
  private final Node node; // DIN Node

  public Player(PooledEngine engine) {
    // Initialize Ashley Entity
    entity = engine.createEntity();
    setupAshleyComponents();

    // Initialize DIN Node
    node = new Node("PlayerNode");
    setupDINProperties();

    // Add the entity to the Ashley engine
    engine.addEntity(entity);
  }

  private void setupAshleyComponents() {
    // Add Ashley components to ashleyEntity
    TextureComponent texture = new TextureComponent(new Texture("img_1.png"));
    PositionComponent position = new PositionComponent(100, 100);
    entity.add(texture).add(position);
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
