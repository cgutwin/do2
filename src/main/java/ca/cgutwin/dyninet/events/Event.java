/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.dyninet.events;

import ca.cgutwin.dyninet.Node;

import java.util.Map;

/**
 * Represents an event within the game. Events are generated as a result of interactions, environmental changes,
 * or other significant occurrences in the game world.
 */
public class Event {
  private final EventType type;
  private final Node source;
  private final Node target;
  private final Map<String, Object> eventData;
  // The priority determines the order of event processing. Higher priority events are processed first.
  private final int priority;

  public Event(EventType type, Node source, Node target, Map<String, Object> eventData, int priority) {
    this.type      = type;
    this.source    = source;
    this.target    = target;
    this.eventData = eventData;
    this.priority  = priority;
  }

  // Constructor, getters, setters, and other methods
  public EventType type() {
    return type;
  }

  public Node source() {
    return source;
  }

  public Node target() {
    return target;
  }

  public int priority() {
    return priority;
  }
}
