package ca.cgutwin.deckedout.ecs.systems;

import ca.cgutwin.deckedout.ecs.components.InputComponent;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputSystem extends EntitySystem {
  private final ComponentMapper<InputComponent> inputM;
  private ImmutableArray<Entity> entities;

  public InputSystem() {
    inputM = ComponentMapper.getFor(InputComponent.class);
  }

  @Override
  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.all(InputComponent.class).get());
  }

  @Override
  public void update(float deltaTime) {
    for (Entity entity: entities) {
      InputComponent input = inputM.get(entity);

      input.left   = Gdx.input.isKeyPressed(Input.Keys.A);
      input.right  = Gdx.input.isKeyPressed(Input.Keys.D);
      input.up     = Gdx.input.isKeyPressed(Input.Keys.W);
      input.down   = Gdx.input.isKeyPressed(Input.Keys.S);
      input.jump   = Gdx.input.isKeyPressed(Input.Keys.SPACE);
      input.sprint = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }
  }
}
