package ca.cgutwin.game.states;

import ca.cgutwin.game.ecs.managers.GameStateManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlayState extends GameState {
  private final OrthographicCamera camera;

  public PlayState(GameStateManager stateManager) {
    super(stateManager);
    this.camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
  }

  @Override
  public void handleInput() {

  }

  @Override
  public void update(float delta) {

  }

  @Override
  public void render(SpriteBatch sb) {
    ScreenUtils.clear(0, 0.2f, 0, 1);

    camera.update();

    // Set the SpriteBatch's projection matrix to the camera's combined matrix
    sb.setProjectionMatrix(camera.combined);
  }

  @Override
  public void dispose() {

  }

  @Override
  public void enter() {

  }

  @Override
  public void exit() {

  }
}
