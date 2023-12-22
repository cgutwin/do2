package ca.cgutwin.deckedout.factories;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public interface Box2DBodyFactory {
  Body createBody(BodyDef bodyDef);
}
