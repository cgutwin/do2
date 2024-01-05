/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.screens;

import ca.cgutwin.deckedout2.GameRunner;
import ca.cgutwin.deckedout2.components.InputComponent;
import ca.cgutwin.deckedout2.components.TextureComponent;
import ca.cgutwin.deckedout2.components.TransformationComponent;
import ca.cgutwin.deckedout2.components.b2d.BodyComponent;
import ca.cgutwin.deckedout2.utils.CollisionObjectProcessor;
import ca.cgutwin.deckedout2.utils.Coordinates;
import ca.cgutwin.deckedout2.utils.commands.movement.MovementCommandFactory;
import ca.cgutwin.deckedout2.utils.commands.movement.MovementEnum;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static ca.cgutwin.deckedout2.utils.Coordinates.PPM;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public class GameScreen implements Screen {
  /* BOX2D */
  private final World world;
  private final Box2DDebugRenderer worldRenderer;
  private final TiledMap map;
  private final OrthogonalTiledMapRenderer mapRenderer;
  private final Viewport viewport;
  private final GameRunner game;


  public GameScreen(GameRunner game) {
    this.game  = game;
    this.world = game.world();

    worldRenderer = new Box2DDebugRenderer();

    map = new TmxMapLoader().load("crypt.tmx");
    int tileSize = ((TiledMapTileLayer) map.getLayers().get(0)).getTileWidth();

    mapRenderer = new OrthogonalTiledMapRenderer(map, 1f);

    // Parse Box2D collision maps from objects in the Tiled world.
    CollisionObjectProcessor collisionObjectProcessor = new CollisionObjectProcessor(world, tileSize);
    for (MapLayer layer: map.getLayers()) {
      if (layer instanceof TiledMapTileLayer tiledMapTileLayer) {
        collisionObjectProcessor.processLayer(tiledMapTileLayer);
      }
    }

    Entity entity = new Entity();
    TextureComponent textureComponent = new TextureComponent();
    TransformationComponent transformationComponent = new TransformationComponent();
    InputComponent inputComponent = new InputComponent();
    BodyComponent bodyComponent = new BodyComponent();

    inputComponent.keyCommands.put(Input.Keys.W, MovementCommandFactory.newMovementCommand(MovementEnum.UP));
    inputComponent.keyCommands.put(Input.Keys.A, MovementCommandFactory.newMovementCommand(MovementEnum.LEFT));
    inputComponent.keyCommands.put(Input.Keys.S, MovementCommandFactory.newMovementCommand(MovementEnum.DOWN));
    inputComponent.keyCommands.put(Input.Keys.D, MovementCommandFactory.newMovementCommand(MovementEnum.RIGHT));

    BodyDef bodyDef = new BodyDef();
    bodyDef.type       = DynamicBody;
    bodyComponent.body = world.createBody(bodyDef);
    bodyComponent.body.setLinearDamping(5);
    textureComponent.texture         = new Texture(Gdx.files.internal("img_1.png"));
    transformationComponent.position = new Coordinates(2, 1);
    bodyComponent.body.getTransform().setPosition(transformationComponent.position.toBox2DCoordinates().coordinates());
    CircleShape shape = new CircleShape();
    shape.setRadius(8f);
    shape.setPosition(transformationComponent.position.coordinates());

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape               = shape;
    fixtureDef.filter.categoryBits = 0x0002;
    fixtureDef.filter.maskBits     = 0x0001;
    bodyComponent.body.createFixture(fixtureDef);
    shape.dispose();

    entity.add(textureComponent).add(transformationComponent).add(inputComponent).add(bodyComponent);
    this.game.engine().addEntity(entity);

    // This ensures that 30x20 tiles are always going to be visible, doesn't matter what the windows resolution is.
    viewport = new FitViewport(30*tileSize, 20*tileSize);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float dt) {
    Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    viewport.getCamera().viewportHeight = Gdx.graphics.getHeight()/PPM;
    viewport.getCamera().viewportWidth  = Gdx.graphics.getWidth()/PPM;
    viewport.apply(true);

    mapRenderer.render();

    mapRenderer.setView((OrthographicCamera) viewport.getCamera());
    worldRenderer.render(world, viewport.getCamera().combined);
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);
  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {
    mapRenderer.dispose();
    map.dispose();
  }
}
