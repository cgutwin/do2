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
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class RectangleBodyFactory extends BaseBodyFactory {
  public RectangleBodyFactory(World world, float tileSize) {
    super(world, tileSize);
  }

  @Override
  protected BodyDef getBodyDef(MapObject object, Coordinates coordinates) {
    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

    /*
    Configures the BodyDef for the rectangle.

    - x*tileSize + tileSize/2f: Calculates the horizontal center of the tile (x position times the size of each tile,
    then adjusted by half the tile size to reach the center).

    - rectangle.getX() - (tileSize - rectangle.getWidth())/2f: Adjusts for the rectangle's horizontal offset within the
    tile. The rectangle might not perfectly align with the tile's edges, so this ensures the body is centered within
    the rectangle.

    - y*tileSize + tileSize/2f: Similar to the x-coordinate calculation, this finds the vertical center of the tile.

    - rectangle.getY() - (tileSize - rectangle.getHeight())/2f: Adjusts for the rectangle's vertical offset within the
    tile, ensuring the body is correctly centered within the rectangle.

    The result is the central point of the rectangle's Box2D body in world coordinates.
    */
    return newBodyDef(coordinates.x()*tileSize + tileSize/2f + rectangle.getX() - (tileSize - rectangle.getWidth())/2f,
                      coordinates.y()*tileSize + tileSize/2f + rectangle.getY() - (tileSize - rectangle.getHeight())/2f);
  }

  @Override
  protected Shape createShape(MapObject object) throws IllegalArgumentException {
    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
    PolygonShape polygonShape = new PolygonShape();

    polygonShape.setAsBox(rectangle.getWidth()/2f, rectangle.getHeight()/2f);

    return polygonShape;
  }

  @Override
  protected FixtureDef createFixtureDef(Shape shape) {
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    return fixtureDef;
  }
}
