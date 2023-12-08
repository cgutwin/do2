package ca.cgutwin.deckedout.game.core.screens;

import ca.cgutwin.deckedout.game.core.GameApplication;
import ca.cgutwin.deckedout.game.ecs.components.PositionComponent;
import ca.cgutwin.deckedout.game.ecs.controllers.CameraController;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.managers.GameStateManager;
import ca.cgutwin.deckedout.game.ecs.managers.MapManager;
import ca.cgutwin.deckedout.game.ecs.systems.*;
import ca.cgutwin.deckedout.game.events.ClankGameEvent;
import ca.cgutwin.deckedout.game.events.HazardGameEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class PlayScreen implements Screen {
  private final GameStateManager gameStateManager;
  private final MapManager mapManager;
  private final MapRenderingSystem mapRenderingSystem;
  private final CameraController cameraController;
  private final MovementSystem movementSystem;
  private final RenderSystem renderSystem;
  private final InputSystem inputSystem;
  private final EventSystem eventSystem;

  private ClankSystem clankSystem;
  private HazardSystem hazardSystem;
  private final Entity player;

  public PlayScreen(GameApplication game, GameStateManager gameStateManager, MapManager mapManager, EventSystem eventSystem) {
    this.mapManager       = mapManager;
    this.gameStateManager = gameStateManager;
    this.eventSystem = eventSystem;
    this.player           = this.gameStateManager.getPlayer();

    mapManager.loadMap("./map.tmx");

    this.cameraController   = new CameraController(800, 480);
    this.mapRenderingSystem = new MapRenderingSystem(mapManager, cameraController.getCamera());
    this.movementSystem     = new MovementSystem(game.getSpriteBatch(), mapManager);
    this.inputSystem        = new InputSystem(gameStateManager.getPlayer().getId(), eventSystem);
    this.renderSystem       = new RenderSystem(game.getSpriteBatch(), cameraController.getCamera());
    this.clankSystem  = new ClankSystem();
    this.hazardSystem = new HazardSystem();

    eventSystem.addListener(ClankGameEvent.class, clankSystem);
    eventSystem.addListener(HazardGameEvent.class, hazardSystem);
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float delta) {
    // Clear the screen
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    PositionComponent positionComponent = player.getComponent(PositionComponent.class);

    cameraController.updateCameraPosition();
    cameraController.setFocalPoint(positionComponent.x, positionComponent.y);

    mapRenderingSystem.update(delta);
    renderSystem.update(delta);
    movementSystem.update(delta);
    inputSystem.update(delta);
  }

  @Override
  public void resize(int i, int i1) {
    cameraController.getViewport().update(i, i1);
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
    mapManager.dispose();
  }
}
