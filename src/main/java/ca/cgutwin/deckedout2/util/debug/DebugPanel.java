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


public class DebugPanel implements EntityListener
{
  private final Stage stage;
  private final Window window;
  private final Engine engine;
  private final Skin skin;
  private SelectBox<Entity> entitySelectBox;
  private SelectBox<Component> componentSelectBox;

  public DebugPanel(Engine engine) {
    this.engine = engine;
    this.engine.addEntityListener(this);

    skin   = new Skin(Gdx.files.internal("skins/commodore64ui/uiskin.json"));
    stage  = new Stage();
    window = new Window("Debug Panel", skin);

    initUI();
  }

  private void initUI() {
    Table table = new Table();
    table.setFillParent(true); // The table will fill the entire stage

    // Entity label
    Label entitiesLabel = new Label("ENTITIES", skin);
    Label componentsLabel = new Label("COMPONENTS", skin);

    // Dropdown (SelectBox) for entities
    entitySelectBox    = new SelectBox<>(skin);
    componentSelectBox = new SelectBox<>(skin);

    // Add components to the table
    table.add(entitiesLabel).pad(10);
    table.row(); // Move to next row
    table.add(entitySelectBox).pad(10).fillX().row();

    table.add(componentsLabel).pad(10).row();
    table.add(componentSelectBox).pad(10).row();

    // Add table to the stage
    stage.addActor(table);

    entitySelectBox.addListener(new ChangeListener()
    {
      @Override
      public void changed(ChangeEvent event, Actor actor) {
        Entity selectedEntity = entitySelectBox.getSelected();
        if (selectedEntity != null) {
          updateComponentsList(selectedEntity);
        }
      }
    });
  }

  private void updateComponentsList(Entity entity) {
    // Assuming you have a method to get components from an entity
    ImmutableArray<Component> components = entity.getComponents();
    componentSelectBox.setItems(components.toArray(Component.class));
  }

  public Stage stage() {
    return stage;
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
    entitySelectBox.setItems(entities.toArray(Entity.class));
  }
}
