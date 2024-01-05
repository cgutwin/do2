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
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

public class PolygonBodyFactory extends BaseBodyFactory {
  public PolygonBodyFactory(World world, float tileSize) {
    super(world, tileSize);
  }

  @Override
  protected BodyDef getBodyDef(MapObject object, Coordinates coordinates) {
    Polygon polygon = ((PolygonMapObject) object).getPolygon();
    BodyDef bodyDef = new BodyDef();
    Coordinates box2dCoordinates = coordinates.toBox2DCoordinates();

    bodyDef.type = StaticBody;
    bodyDef.position.set(box2dCoordinates.x()*tileSize + polygon.getX(),
                         box2dCoordinates.y()*tileSize + polygon.getY());

    return bodyDef;
  }

  @Override
  protected Shape createShape(MapObject object) {
    Polygon polygon = ((PolygonMapObject) object).getPolygon();

    PolygonShape polygonShape = new PolygonShape();
    polygonShape.set(polygon.getVertices());

    return polygonShape;
  }

  @Override
  protected FixtureDef createFixtureDef(Shape shape) {
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    return fixtureDef;
  }
}
