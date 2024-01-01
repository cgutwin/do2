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
import com.badlogic.gdx.graphics.Color;

public class LightComponent implements Component {
  public Color color;
  public float intensity;
  public int size;
  public float lightOffsetX;
  public float lightOffsetY;

  public LightComponent(Color color, float intensity, int size) {
    this(color, intensity, size, 0, 0);
  }

  public LightComponent(Color color, float intensity, int size, float lightOffsetX, float lightOffsetY) {
    this.color        = color;
    this.intensity    = intensity;
    this.size         = size;
    this.lightOffsetX = lightOffsetX;
    this.lightOffsetY = lightOffsetY;
  }
}
