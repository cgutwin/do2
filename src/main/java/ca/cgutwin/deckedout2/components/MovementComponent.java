/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class MovementComponent implements Component {
  public float time = 0.0f;
  public boolean isLooping = false;
  public Rectangle bounds;
  private MovementStates state = MovementStates.NORMAL;

  public MovementComponent(float x, float y, float width, float height) {
    this.bounds = new Rectangle(x, y, width, height);
  }

  public void set(MovementStates newState) {
    state = newState;
    time  = 0.0f;
  }

  public MovementStates get() {
    return state;
  }

  public enum MovementStates {
    NORMAL, JUMP, FALLING, MOVING, HIT
  }
}
