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
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

public class EllipseBodyFactory extends BaseBodyFactory {
  public EllipseBodyFactory(World world, float tileSize) {
    super(world, tileSize);
  }

  @Override
  protected BodyDef getBodyDef(MapObject object, Coordinates coordinates) {
    Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
    BodyDef bodyDef = new BodyDef();
    Coordinates box2dCoordinates = coordinates.toBox2DCoordinates();

    bodyDef.type = StaticBody;
    bodyDef.position.set(box2dCoordinates.x()*tileSize + tileSize/2f + ellipse.x,
                         box2dCoordinates.y()*tileSize + tileSize/2f + ellipse.y);

    return bodyDef;
  }

  @Override
  protected Shape createShape(MapObject object) {
    Ellipse ellipse = ((EllipseMapObject) object).getEllipse();

    CircleShape circleShape = new CircleShape();
    circleShape.setRadius(ellipse.width/2);
    circleShape.setPosition(new Vector2(ellipse.x + ellipse.width/2, ellipse.y + ellipse.height/2));

    return circleShape;
  }

  @Override
  protected FixtureDef createFixtureDef(Shape shape) {
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    return fixtureDef;
  }
}
