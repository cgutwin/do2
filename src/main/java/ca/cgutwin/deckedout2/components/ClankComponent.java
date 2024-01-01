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

public class ClankComponent implements Component {
  private float clankValue = 0.0f;

  // Method to increase clank, could be more complex depending on your game logic
  public void increaseClank(float amount) {
    clankValue += amount;
  }

  public float clankValue() {
    return clankValue;
  }
}
