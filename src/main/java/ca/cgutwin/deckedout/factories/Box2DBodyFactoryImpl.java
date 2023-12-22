package ca.cgutwin.deckedout.factories;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DBodyFactoryImpl implements Box2DBodyFactory {
  private final World world;

  public Box2DBodyFactoryImpl(World world) {
    this.world = world;
  }

  @Override
  public Body createBody(BodyDef bodyDef) {
    return world.createBody(bodyDef);
  }
}
