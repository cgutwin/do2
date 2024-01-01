/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.events;

/**
 * Represents an event within the game. Events are generated as a result of interactions, environmental changes,
 * or other significant occurrences in the game world.
 */
public class GameEvent {
  private final EventType type;
  private final int priority;

  public GameEvent(EventType type, int priority) {
    this.type     = type;
    this.priority = priority;
  }

  public EventType type() {
    return type;
  }

  public int priority() {
    return priority;
  }
}
