package ca.cgutwin.deckedout.screens;

import ca.cgutwin.deckedout.core.GameStateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseOverlay {
  private final Skin skin;
  private final GameStateManager gsm;
  Label debugLabel;
  private final Stage stage;
  private boolean visible = false;

  public PauseOverlay(GameStateManager gsm) {
    this.gsm = gsm;

    skin  = new Skin(Gdx.files.internal("skins/debug-skin/debug-skin.json")); // Load your skin file
    stage = new Stage(new ScreenViewport());

    setupUI();
  }

  private void setupUI() {

    Table table = new Table();
    table.setFillParent(true); // Table will take the size of the stage
    debugLabel = new Label("Paused", skin);
    table.add(debugLabel).pad(10);
    stage.addActor(table);
  }

  public void toggleVisibility() {
    visible = !visible;
  }

  public void render() {
    if (!visible) { return; }

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  public void dispose() {
    stage.dispose();
    skin.dispose();
  }
}
