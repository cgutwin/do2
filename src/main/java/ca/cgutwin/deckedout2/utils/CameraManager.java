/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraManager {

  private final float worldWidth;
  private final float worldHeight;
  private OrthographicCamera camera;
  private Viewport viewport;

  /**
   * Constructor for CameraManager.
   *
   * @param worldWidth  The width of the world in units.
   * @param worldHeight The height of the world in units.
   */
  public CameraManager(float worldWidth, float worldHeight) {
    this.worldWidth  = worldWidth;
    this.worldHeight = worldHeight;
    initializeCamera();
  }

  private void initializeCamera() {
    camera   = new OrthographicCamera();
    viewport = new ExtendViewport(worldWidth, worldHeight, camera);
    camera.position.set(worldWidth/2f, worldHeight/2f, 0);
    //    camera.zoom = 0.5F;
    camera.update();
  }

  /**
   * Updates the camera viewport dimensions.
   * Typically called in the resize method of a screen.
   *
   * @param width  The new width.
   * @param height The new height.
   */
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  /**
   * Gets the camera instance.
   *
   * @return The OrthographicCamera.
   */
  public OrthographicCamera getCamera() {
    return camera;
  }

  /**
   * Centers the camera on given coordinates.
   *
   * @param x The x-coordinate to center on.
   * @param y The y-coordinate to center on.
   */
  public void centerOn(float x, float y) {
    camera.position.set(x, y, 0);
    camera.update();
  }

  public void centerOn(Vector2 vector) {
    camera.position.set(vector, 0);
    camera.update();
  }

  // Additional methods to manipulate the camera (e.g., zoom, shake) can be added here.
}
