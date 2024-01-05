/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.utils;

import com.badlogic.gdx.math.Vector2;

public final class Coordinates {
  // PPM: LibGDX Pixels per Meter in Box2D
  public static final float PPM = 16f;
  private float x;
  private float y;
  private Vector2 coordinates;

  public Coordinates() {
    this(0, 0);
  }

  public Coordinates(float x, float y) {
    this.x           = x;
    this.y           = y;
    this.coordinates = new Vector2(x, y);
  }

  public Coordinates(Vector2 coordinates) {
    this.x           = coordinates.x;
    this.y           = coordinates.y;
    this.coordinates = coordinates;
  }

  public void set(float x, float y) {
    this.setX(x);
    this.setY(y);
  }

  private void setX(float x) {
    this.x             = x;
    this.coordinates.x = x;
  }

  private void setY(float y) {
    this.y             = y;
    this.coordinates.y = y;
  }

  public void setCoordinates(Vector2 coordinates) {
    this.coordinates = coordinates;
  }

  public Vector2 coordinates() { return coordinates; }

  public Coordinates toBox2DCoordinates() {
    return new Coordinates(x/PPM, y/PPM);
  }

  public Coordinates toLibGDXCoordinates() {
    return new Coordinates(x*PPM, y*PPM);
  }

  public float x() { return x; }

  public float y() { return y; }
}
