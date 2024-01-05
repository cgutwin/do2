/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.systems;

import ca.cgutwin.deckedout2.components.*;
import ca.cgutwin.deckedout2.components.b2d.BodyComponent;
import ca.cgutwin.deckedout2.components.b2d.CollisionComponent;
import ca.cgutwin.deckedout2.utils.Coordinates;
import ca.cgutwin.deckedout2.utils.commands.movement.MovementCommandFactory;
import ca.cgutwin.deckedout2.utils.commands.movement.MovementEnum;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import static ca.cgutwin.deckedout2.components.TypeComponent.EntityType.PLAYER;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public class PlayerInitializationSystem extends EntitySystem {
  private final World world;
  private boolean playerCreated = false;

  public PlayerInitializationSystem(World world) {
    this.world = world;
  }

  @Override
  public void update(float deltaTime) {
    if (!playerCreated) {
      createPlayer();
      playerCreated = true;
    }
  }

  private void createPlayer() {
    Entity playerEntity = new Entity();
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = DynamicBody;

    BodyComponent bodyComponent = getEngine().createComponent(BodyComponent.class);
    TransformationComponent transformationComponent = getEngine().createComponent(TransformationComponent.class);
    PlayerComponent playerComponent = getEngine().createComponent(PlayerComponent.class);
    CollisionComponent collisionComponent = getEngine().createComponent(CollisionComponent.class);
    TypeComponent typeComponent = getEngine().createComponent(TypeComponent.class);
    TextureComponent textureComponent = getEngine().createComponent(TextureComponent.class);
    InputComponent inputComponent = getEngine().createComponent(InputComponent.class);

    inputComponent.keyCommands.put(Input.Keys.W, MovementCommandFactory.newMovementCommand(MovementEnum.UP));
    inputComponent.keyCommands.put(Input.Keys.A, MovementCommandFactory.newMovementCommand(MovementEnum.LEFT));
    inputComponent.keyCommands.put(Input.Keys.S, MovementCommandFactory.newMovementCommand(MovementEnum.DOWN));
    inputComponent.keyCommands.put(Input.Keys.D, MovementCommandFactory.newMovementCommand(MovementEnum.RIGHT));

    transformationComponent.position = new Coordinates(0, 0);
    typeComponent.type               = PLAYER;
    bodyDef.position.set(transformationComponent.position.coordinates());
    bodyComponent.body = world.createBody(bodyDef);
    bodyComponent.body.setUserData(playerEntity);
    bodyComponent.body.setLinearDamping(5);

    CircleShape shape = new CircleShape();
    shape.setRadius(8f);
    shape.setPosition(transformationComponent.position.coordinates());

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape               = shape;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits     = 0x0001;
    bodyComponent.body.createFixture(fixtureDef);
    shape.dispose();

    textureComponent.texture = new Texture(Gdx.files.internal("img_1.png"));

    playerEntity.add(bodyComponent)
                .add(transformationComponent)
                .add(playerComponent)
                .add(collisionComponent)
                .add(textureComponent)
                .add(inputComponent)
                .add(typeComponent);

    getEngine().addEntity(playerEntity);
  }
}
