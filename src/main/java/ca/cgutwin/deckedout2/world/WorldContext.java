/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.world;

public class WorldContext {
  private float hazard = 0.0F;
  private float clank = 0.0F;

  public WorldContext() {
  }

  public float clank() {
    return clank;
  }

  public void increaseClank() {
    this.clank += 1.0F;
  }

  public void increaseHazard() {
    increaseHazardBy(1F);
  }

  public void increaseHazardBy(float amount) {
    this.hazard += amount;
  }

  public float hazard() {
    return hazard;
  }
}
