/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
  private Vector2 position;

  public PositionComponent(float x, float y) {
    this.position = new Vector2(x, y);
  }

  public Vector2 position() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }
}
