package ca.cgutwin.deckedout.debug;

import ca.cgutwin.deckedout.GameState;
import ca.cgutwin.deckedout.core.GameStateManager;
import ca.cgutwin.deckedout.states.PlayState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class DebugOverlay {
  private final Skin skin;
  private final GameStateManager gsm;
  Label debugLabel;
  private final Stage stage;
  private boolean visible = false;

  public DebugOverlay(GameStateManager gsm) {
    this.gsm = gsm;
    skin     = new Skin(Gdx.files.internal("skins/debug-skin/debug-skin.json")); // Load your skin file
    stage    = new Stage(new ScreenViewport());

    Gdx.input.setInputProcessor(stage);
    setupUI();
  }

  private void setupUI() {
    Table table = new Table();
    table.setFillParent(true); // Table will take the size of the stage
    debugLabel = new Label("Debug Info", skin);

    table.add(debugLabel).pad(10);
    table.row();
    table.add(new Label("PlayStates:", skin)).pad(10);
    List<String> itemList = new List<>(skin);
    Array<String> items = new Array<>();
    for (PlayState.State state: PlayState.State.values()) {
      items.add(state.toString());
    }
    itemList.setItems(items);

    // Add the list to a scroll pane for scrolling functionality
    ScrollPane scrollPane = new ScrollPane(itemList, skin);

    itemList.addListener(new ChangeListener() {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        String selectedItem = itemList.getSelected();
        GameState state = gsm.peek();

        if (state instanceof PlayState) {
          ((PlayState) state).setPlayScreen(PlayState.State.valueOf(selectedItem));
        }
      }
    });

    stage.addActor(scrollPane);
    stage.addActor(table);
  }

  public void toggleVisibility() {
    visible = !visible;
    System.out.println("Debug visible: " + visible);
  }

  public void render() {
    if (!visible) { return; }
    updateDebugInfo();

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();
  }

  private void updateDebugInfo() {
    // Update the label text with debug information
    debugLabel.setText("Current State: " + gsm.peek());
    // You can add more information as needed
  }

  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  public void dispose() {
    stage.dispose();
    skin.dispose();
  }
}
