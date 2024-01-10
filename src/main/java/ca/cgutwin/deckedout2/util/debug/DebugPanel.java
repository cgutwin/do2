package ca.cgutwin.deckedout2.util.debug;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class DebugPanel implements EntityListener
{
  private final Stage stage;
  private final Engine engine;
  private final Skin skin;
  private final Viewport viewport = new ScreenViewport();
  private List<Entity> entityList;
  private List<Component> componentList;
  private boolean visible;

  public DebugPanel(Engine engine) {
    this.engine = engine;
    this.engine.addEntityListener(this);

    skin  = new Skin(Gdx.files.internal("skins/commodore64ui/uiskin.json"));
    stage = new Stage(viewport);

    initUI();
  }


  private void initUI() {
    Table table = new Table();
    table.setBackground(skin.getDrawable("window"));
    table.setFillParent(true);

    Label label = new Label("DEBUG", skin);
    table.add(label).align(Align.left);

    TextButton textButton = new TextButton("X", skin);
    textButton.setName("panel_close");
    table.add(textButton).align(Align.right);

    table.row();
    Table table1 = new Table();
    table1.align(Align.topLeft);

    label = new Label("Entities", skin);
    table1.add(label).align(Align.left);

    table1.row();

    entityList = new List<>(skin);
    entityList.setName("entitiesList");
    ScrollPane scrollPane = new ScrollPane(entityList, skin);
    table1.add(scrollPane).expandY().align(Align.topLeft).uniformY();

    table1.row();
    label = new Label("Components", skin);
    table1.add(label).align(Align.left);

    table1.row();

    componentList = new List<>(skin);
    componentList.setName("componentsList");
    scrollPane = new ScrollPane(componentList, skin);
    table1.add(scrollPane).expandY().align(Align.topLeft).uniformY();
    table.add(table1).pad(16.0f).growY().align(Align.left);

    table1 = new Table();
    table1.align(Align.left);

    label = new Label("Data", skin);
    table1.add(label).expandX().align(Align.left);

    table1.row();
    List<String> dataList = new List<>(skin);
    dataList.setName("dataList");
    table1.add(dataList).growY();
    table.add(table1).growY();
    stage.addActor(table);


    entityList.addListener(new ChangeListener()
    {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        Entity selectedEntity = entityList.getSelected();
        if (selectedEntity != null) {
          updateComponentsList(selectedEntity);
        }
      }
    });
  }

  private void updateComponentsList(Entity entity) {
    // Assuming you have a method to get components from an entity
    ImmutableArray<Component> components = entity.getComponents();
    componentList.setItems(components.toArray(Component.class));
  }

  public Stage stage() {
    return stage;
  }

  public void render(float delta) {
    if (visible) {
      stage.act(delta);
      stage.draw();
    }
  }

  @Override
  public void entityAdded(Entity entity) {
    updateEntitiesList();
  }

  @Override
  public void entityRemoved(Entity entity) {
    updateEntitiesList();
  }

  private void updateEntitiesList() {
    ImmutableArray<Entity> entities = engine.getEntities();
    entityList.setItems(entities.toArray(Entity.class));
  }

  public boolean shown() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }
}
