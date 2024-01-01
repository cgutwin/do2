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

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
  private static final List<GameEventListener> listeners = new ArrayList<>();
  private static float lastPublished;

  /**
   * @param interval seconds of being active.
   */
  public static void publishEvery(float interval, GameEvent event) {
    lastPublished += Gdx.graphics.getDeltaTime();
    if (lastPublished >= interval) {
      publish(event);
      lastPublished = 0; // Reset the timer
    }
  }

  public static void publish(GameEvent event) {
    for (GameEventListener listener: listeners) {
      listener.handleEvent(event);
    }
  }

  public void subscribe(GameEventListener listener) {
    listeners.add(listener);
  }
}