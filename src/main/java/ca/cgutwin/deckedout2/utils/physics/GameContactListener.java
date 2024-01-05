/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.utils.physics;

import ca.cgutwin.deckedout2.components.b2d.CollisionComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;

public class GameContactListener implements ContactListener {
  @Override
  public void beginContact(Contact contact) {
    Fixture fixtureA = contact.getFixtureA();
    Fixture fixtureB = contact.getFixtureB();

    System.out.println(fixtureA);
    System.out.println(fixtureB);
  }

  @Override
  public void endContact(Contact contact) {
    System.out.println("Contact end");
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
  }

  private void entityCollision(Entity ent, Fixture fb) {
    if (fb.getBody().getUserData() instanceof final Entity colEnt) {

      CollisionComponent col = ent.getComponent(CollisionComponent.class);
      CollisionComponent colb = colEnt.getComponent(CollisionComponent.class);

      if (col != null) { col.collisionEntity = colEnt; }
      else if (colb != null) { colb.collisionEntity = ent; }
    }
  }
}
