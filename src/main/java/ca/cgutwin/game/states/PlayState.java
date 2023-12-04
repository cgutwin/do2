package ca.cgutwin.game.states;

import ca.cgutwin.game.core.camera.CameraController;
import ca.cgutwin.game.core.camera.movement.MoveCameraUpCommand;
import ca.cgutwin.game.core.camera.movement.StopCameraCommand;
import ca.cgutwin.game.ecs.managers.GameStateManager;
import ca.cgutwin.game.io.InputManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlayState extends GameState {
  private final OrthographicCamera camera;
  Texture img = new Texture(Gdx.files.internal("img.png"));

  public PlayState(GameStateManager stateManager) {
    super(stateManager);

    this.camera = new OrthographicCamera();

    CameraController cameraController = new CameraController(camera);
    InputManager inputManager = InputManager.getInstance();

    inputManager.bindKeyDown(Input.Keys.W, new MoveCameraUpCommand(cameraController));
    inputManager.bindKeyUp(Input.Keys.W, new StopCameraCommand(cameraController));
  }

  @Override
  public void update(float delta) {

  }

  @Override
  public void render(SpriteBatch sb) {
    ScreenUtils.clear(0, 0.2f, 0, 1);

    // Set the SpriteBatch's projection matrix to the camera's combined matrix
    sb.setProjectionMatrix(camera.combined);
    sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

    sb.begin();
    sb.draw(img, 0, 0);
    sb.end();
  }

  @Override
  public void dispose() {

  }

  @Override
  public void hide(SpriteBatch sb) {
    img.dispose();
    sb.dispose();
  }

  @Override
  public void enter() {

  }

  @Override
  public void exit() {

  }
}
