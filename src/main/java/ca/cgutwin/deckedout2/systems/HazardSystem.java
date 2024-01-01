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

import ca.cgutwin.deckedout2.events.GameEvent;
import ca.cgutwin.deckedout2.events.GameEventListener;
import ca.cgutwin.deckedout2.world.WorldContext;

public class HazardSystem implements GameEventListener {

  private final WorldContext context;

  public HazardSystem(WorldContext context) {
    this.context = context;
  }

  @Override
  public void handleEvent(GameEvent event) {
    switch (event.type()) {
      case CLANK, HAZARD -> context.increaseHazardBy(calculateHazardBasedOnClank(1F));
    }
  }

  private float calculateHazardBasedOnClank(float clankLevel) {
    return clankLevel; // Simplified example
  }
}
