package ca.cgutwin.deckedout.core.screens;

import ca.cgutwin.deckedout.DeckedOut;
import ca.cgutwin.deckedout.ecs.systems.InputSystem;
import ca.cgutwin.deckedout.ecs.systems.MovementSystem;
import ca.cgutwin.deckedout.ecs.systems.RenderSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;

public class WorldScreen implements Screen {
  private final DeckedOut game;
  private final Engine engine;

  public WorldScreen(DeckedOut game) {
    this.game = game;
    engine    = game.engine();

    engine.addEntity(game.player());

    engine.addSystem(new InputSystem());
    engine.addSystem(new RenderSystem(game.spriteBatch()));
    engine.addSystem(new MovementSystem());
  }

  @Override
  public void show() {
  }

  @Override
  public void render(float dt) {
    engine.update(dt);
    game.worldManager().update(dt);
  }

  @Override
  public void resize(int i, int i1) {

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
    game.player().dispose();
  }
}
