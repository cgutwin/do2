package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.core.GameStateManager;
import ca.cgutwin.deckedout.debug.DebugOverlay;
import ca.cgutwin.deckedout.states.PlayState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DeckedOut extends Game {
  private GameStateManager gsm;
  private SpriteBatch batch;
  private DebugOverlay debugOverlay;

  public DeckedOut() {
    super();
  }

  @Override
  public void create() {
    gsm          = new GameStateManager();
    batch        = new SpriteBatch();
    debugOverlay = new DebugOverlay(gsm);

    gsm.push(new PlayState(this, gsm));
  }

  @Override
  public void dispose() {
    super.dispose();

    gsm.pop();
    debugOverlay.dispose();
  }

  @Override
  public void pause() {
    super.pause();
  }

  @Override
  public void resume() {
    super.resume();
  }

  @Override
  public void render() {
    super.render();
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

    if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
      debugOverlay.toggleVisibility();
    }
    debugOverlay.render();

    gsm.update(Gdx.graphics.getDeltaTime());
    gsm.render();
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);

    debugOverlay.resize(width, height);
  }

  @Override
  public void setScreen(Screen screen) {
    super.setScreen(screen);
  }

  @Override
  public Screen getScreen() {
    return super.getScreen();
  }
}
