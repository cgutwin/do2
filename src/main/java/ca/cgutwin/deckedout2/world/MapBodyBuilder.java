/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

/**
 * Builds physics bodies for a Tiled map in a Box2D world.
 *
 * @see <a href="https://lyze.dev/2021/03/25/libGDX-Tiled-Box2D-example-tiles/">libGDX Tiled Box2D - tile collisions</a>
 */
public class MapBodyBuilder
{
  private final World world;
  private final float tileSize;

  /**
   * Constructor for MapBodyBuilder.
   *
   * @param world    The Box2D world where bodies will be created.
   * @param tileSize The size of each tile in the world, used for scaling.
   */
  public MapBodyBuilder(World world, float tileSize) {
    this.world    = world;
    this.tileSize = tileSize;
  }

  /**
   * Creates a Box2D body based on the type of MapObject.
   *
   * @param cell The cell from the Tiled map layer.
   * @param x    The x-coordinate of the cell in the map.
   * @param y    The y-coordinate of the cell in the map.
   */
  public Body createBody(TiledMapTileLayer.Cell cell, int x, int y) {
    if (cell == null) { return null; }

    MapObjects objects = cell.getTile().getObjects();
    if (objects.getCount() != 1) { return null; }

    MapObject object = objects.get(0);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.filter.categoryBits = 0x0001;
    fixtureDef.filter.maskBits     = 0x0002;

    return switch (object) {
      case RectangleMapObject rectangleMapObject -> createRectangleBody(rectangleMapObject, fixtureDef, x, y);
      case EllipseMapObject ellipseMapObject -> createEllipseBody(ellipseMapObject, fixtureDef, x, y);
      case PolygonMapObject polygonMapObject -> createPolygonBody(polygonMapObject, fixtureDef, x, y);
      case null, default -> null;
    };
  }

  // The following methods implement the specifics of creating Box2D bodies
  // for different shapes (rectangle, ellipse, and polygon) based on the
  // properties of the map objects.

  /**
   * Creates a rectangular Box2D body from a RectangleMapObject.
   * <p>
   * This method sets up a body definition, calculates the position,
   * and creates a rectangular shape in the Box2D world.
   *
   * @param rectangleObject The RectangleMapObject from the Tiled map.
   * @param x               The x-coordinate of the cell in the map.
   * @param y               The y-coordinate of the cell in the map.
   */
  private Body createRectangleBody(RectangleMapObject rectangleObject, FixtureDef fixtureDef, int x, int y) {
    Rectangle rectangle = rectangleObject.getRectangle(); // Extracts the rectangle from the map object

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
    BodyDef bodyDef = getBodyDef(x*tileSize + tileSize/2f + rectangle.getX() - (tileSize - rectangle.getWidth())/2f,
                                 y*tileSize + tileSize/2f + rectangle.getY() - (tileSize - rectangle.getHeight())/2f);

    Body body = world.createBody(bodyDef); // Creates the body in the Box2D world

    PolygonShape polygonShape = new PolygonShape(); // Defines a polygon shape for the rectangle
    polygonShape.setAsBox(rectangle.getWidth()/2f, rectangle.getHeight()/2f);

    fixtureDef.shape = polygonShape;

    body.createFixture(fixtureDef); // Attaches the shape to the body
    polygonShape.dispose();

    return body;
  }

  /**
   * Creates a circular Box2D body from a EllipseMapObject.
   * <p>
   * This method sets up a body definition, calculates the position,
   * and creates a circular shape in the Box2D world.
   *
   * @param ellipseObject The EllipseMapObject from the Tiled map.
   * @param x             The x-coordinate of the cell in the map.
   * @param y             The y-coordinate of the cell in the map.
   * @throws IllegalArgumentException Only circles are allowed.
   */
  private Body createEllipseBody(EllipseMapObject ellipseObject, FixtureDef fixtureDef, int x, int y)
          throws IllegalArgumentException {
    Ellipse ellipse = ellipseObject.getEllipse();

    /*
    Configures the BodyDef for an ellipse.

    The position is calculated based on the tile's location and the ellipse's offset within the tile.
    - (x*tileSize + tileSize/2f + ellipse.x): Computes the central X-coordinate of the ellipse, factoring in the
    tile's position and ellipse's offset.
    - (y*tileSize + tileSize/2f + ellipse.y): Similar computation for the central Y-coordinate.
    */
    BodyDef bodyDef = getBodyDef(x*tileSize + tileSize/2f + ellipse.x, y*tileSize + tileSize/2f + ellipse.y);

    if (ellipse.width != ellipse.height) { throw new IllegalArgumentException("Only circles are allowed."); }

    Body body = world.createBody(bodyDef);

    CircleShape circleShape = new CircleShape();
    circleShape.setRadius(ellipse.width/2f);

    fixtureDef.shape = circleShape;

    body.createFixture(fixtureDef);
    circleShape.dispose();

    return body;
  }

  /**
   * Creates a polygon Box2D body from a PolygonMapObject.
   * <p>
   * This method sets up a body definition, calculates the position,
   * and creates a polygon shape in the Box2D world.
   *
   * @param polygonObject The PolygonMapObject from the Tiled map.
   * @param x             The x-coordinate of the cell in the map.
   * @param y             The y-coordinate of the cell in the map.
   */
  private Body createPolygonBody(PolygonMapObject polygonObject, FixtureDef fixtureDef, int x, int y) {
    Polygon polygon = polygonObject.getPolygon();

    /*
    Configures the BodyDef for a polygon, defining its position in the Box2D world.

    The position is determined by the tile's location on the map and the polygon's position within the tile.
    - (x*tileSize + polygon.getX()): Calculates the X-coordinate of the polygon's center, incorporating the tile's
    X-position and the polygon's offset.
    - (y*tileSize + polygon.getY()): Similar calculation for the Y-coordinate.
    */
    BodyDef bodyDef = getBodyDef(x*tileSize + polygon.getX(), y*tileSize + polygon.getY());

    Body body = world.createBody(bodyDef);

    PolygonShape polygonShape = new PolygonShape();
    polygonShape.set(polygon.getVertices());
    fixtureDef.shape = polygonShape;

    body.createFixture(fixtureDef);
    polygonShape.dispose();

    return body;
  }

  /**
   * Creates a new BodyDef with specified position coordinates.
   * <p>
   * This method initializes a BodyDef object, a core component in Box2D for defining bodies.
   * <ul>
   *   <li>Sets the body type to StaticBody, meaning this body won't be affected by physics like gravity but can
   *   interact with other moving bodies.</li>
   *   <li>The position is set based on the passed x and y coordinates, determining where in the Box2D world the body
   *   will be placed.</li>
   * </ul>
   *
   * @param x The x-coordinate in the Box2D world.
   * @param y The y-coordinate in the Box2D world.
   * @return A configured BodyDef object.
   */
  private BodyDef getBodyDef(float x, float y) {
    BodyDef bodyDef = new BodyDef();

    bodyDef.type = StaticBody;
    bodyDef.position.set(x, y);

    return bodyDef;
  }
}
