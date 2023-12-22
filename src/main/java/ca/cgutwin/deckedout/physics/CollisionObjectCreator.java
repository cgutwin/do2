package ca.cgutwin.deckedout.physics;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class CollisionObjectCreator {
  private final World world;
  private final TiledMap tiledMap;
  private final float PPM; // Pixels Per Meter

  public CollisionObjectCreator(World world, TiledMap tiledMap, float ppm) {
    this.world    = world;
    this.tiledMap = tiledMap;
    this.PPM      = ppm;
  }

  public void createCollisionObjects() {
    MapLayer collisionLayer = tiledMap.getLayers().get("_CollisionLayer");
    for (MapObject object: collisionLayer.getObjects()) {
      if (object instanceof RectangleMapObject) {
        createRectangleBody((RectangleMapObject) object);
      }
      // Add similar conditions for other shape types if needed
    }
  }

  private void createRectangleBody(RectangleMapObject rectangleObject) {
    Rectangle rectangle = rectangleObject.getRectangle();
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set((rectangle.x + rectangle.width*0.5f)/PPM, (rectangle.y + rectangle.height*0.5f)/PPM);

    Body body = world.createBody(bodyDef);

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(rectangle.width*0.5f/PPM, rectangle.height*0.5f/PPM);

    body.createFixture(shape, 1.0f);
    shape.dispose();
  }
}
