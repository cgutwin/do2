package ca.cgutwin.deckedout2.util.debug;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


public class DebugPanel
{
  private final Stage stage;
  private final Window window;
  private final List<Entity> entityList;
  private final Engine engine;

  public DebugPanel(Engine engine) {
    this.engine = engine;
    Skin skin = new Skin(Gdx.files.internal("skins/commodore64ui/uiskin.json"));
    stage      = new Stage();
    window     = new Window("Debug Panel", skin);
    entityList = new List<>(skin);

    window.add(entityList).row();
    stage.addActor(window);

    entityList.addListener(new ChangeListener()
    {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        updateEntities();
      }
    });
  }

  public void updateEntities() {
    ImmutableArray<Entity> entities = engine.getEntities();
    entityList.clearItems();
    for (Entity entity: entities) {
      entityList.getItems().add(entity);
    }
  }

  public void render(float delta) {
    stage.act(delta);
    stage.draw();
  }

  public void show() {
    window.setVisible(true);
  }

  public void hide() {
    window.setVisible(false);
  }
}
