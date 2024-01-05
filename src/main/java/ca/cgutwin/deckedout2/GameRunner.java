/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2;

import ca.cgutwin.deckedout2.screens.GameScreen;
import ca.cgutwin.deckedout2.systems.CollisionSystem;
import ca.cgutwin.deckedout2.systems.InputSystem;
import ca.cgutwin.deckedout2.systems.PhysicsSystem;
import ca.cgutwin.deckedout2.systems.RenderingSystem;
import ca.cgutwin.deckedout2.utils.physics.GameContactListener;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameRunner extends Game {
  private World world;
  private SpriteBatch batch;
  private PooledEngine engine;

  public World world() {
    return world;
  }

  public PooledEngine engine() {
    return engine;
  }

  @Override
  public void create() {
    world = new World(new Vector2(0, 0), false);
    world.setContactListener(new GameContactListener());
    batch  = new SpriteBatch();
    engine = new PooledEngine();

    engine.addSystem(new InputSystem());
    engine.addSystem(new CollisionSystem());
    engine.addSystem(new PhysicsSystem(world));
    engine.addSystem(new RenderingSystem(batch));
    //    engine.addSystem(new PlayerInitializationSystem(world));
    //    engine.addSystem(new RenderingSystem(batch, new TextureRenderer()));

    setScreen(new GameScreen(this));
  }

  @Override
  public void dispose() {
    super.dispose();

    if (world != null) { world.dispose(); }
    if (batch != null) { batch.dispose(); }
  }

  @Override
  public void render() {
    super.render();

    engine.update(Gdx.graphics.getDeltaTime());
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
  }

  // Getters and Setters
  public SpriteBatch batch() {
    return batch;
  }
}
