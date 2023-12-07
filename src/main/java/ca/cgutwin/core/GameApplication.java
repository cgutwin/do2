package ca.cgutwin.core;

import ca.cgutwin.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends Game {
  private SpriteBatch batch;

  @Override
  public void create() {
    this.setScreen(new GameScreen());
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  @Override
  public void render() {
    super.render(); // Important! This will render the active screen.
  }

  public SpriteBatch getBatch() {
    return batch;
  }
}
