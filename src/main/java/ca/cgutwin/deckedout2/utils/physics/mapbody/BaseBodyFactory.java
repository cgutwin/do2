/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.utils.physics.mapbody;

import ca.cgutwin.deckedout2.utils.Coordinates;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

public abstract class BaseBodyFactory implements BodyFactory {
  protected World world;
  protected float tileSize;

  public BaseBodyFactory(World world, float tileSize) {
    this.world    = world;
    this.tileSize = tileSize;
  }

  @Override
  public void createBody(MapObject object, Coordinates coordinates) {
    BodyDef bodyDef = getBodyDef(object, coordinates);
    Body body = world.createBody(bodyDef);

    Shape shape = createShape(object);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape               = shape;
    fixtureDef.filter.categoryBits = 0x0001;
    fixtureDef.filter.maskBits     = 0x0002;

    body.createFixture(fixtureDef);
    shape.dispose();
  }

  protected abstract BodyDef getBodyDef(MapObject object, Coordinates coordinates);

  protected abstract Shape createShape(MapObject object) throws IllegalArgumentException;

  protected BodyDef newBodyDef(float x, float y) {
    BodyDef bodyDef = new BodyDef();

    bodyDef.type = StaticBody;
    bodyDef.position.set(x, y);

    return bodyDef;
  }

  protected abstract FixtureDef createFixtureDef(Shape shape);
}