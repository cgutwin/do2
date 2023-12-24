/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.dyninet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an entity in the game world. Each node can be a character, object, or environment element.
 * The Node class is a fundamental part of the Dynamic Interaction Network, serving as the primary
 * actor within the game's interaction system.
 */
public class Node {
  private final String id;
  private final Map<String, Object> properties;
  private final List<InteractionRule> interactionRules;

  /**
   * Constructs a new Node with a unique identifier.
   *
   * @param id A unique identifier for the node. The ID is used to distinguish between different nodes
   *           and tracks interactions and influences within the game world.
   */
  public Node(String id) {
    this.id               = id;
    this.properties       = new HashMap<>();
    this.interactionRules = new ArrayList<>();
  }

  /**
   * Adds a property to the node.
   * <p>
   * Properties are key-value pairs that can store various information about the node, such as
   * its state, characteristics, or any game-specific data. This flexible system allows nodes
   * to be adapted to a wide range of scenarios and game mechanics.
   *
   * @param key   The property key.
   * @param value The property value.
   */
  public void addProperty(String key, Object value) {
    properties.put(key, value);
  }

  /**
   * Adds an interaction rule to the node.
   * <p>
   * Interaction rules define how this node interacts with other nodes. By attaching these rules,
   * the node's behavior in response to other entities can be dynamically defined and adjusted.
   * This allows for complex and context-sensitive interactions in the game world.
   *
   * @param rule The interaction rule to be added.
   */
  public void addInteractionRule(InteractionRule rule) {
    interactionRules.add(rule);
  }

  // Getters and Setters
  public String getId() {
    return id;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public List<InteractionRule> getInteractionRules() {
    return interactionRules;
  }
}
