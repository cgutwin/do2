package ca.cgutwin.deckedout2.input.processors;

import ca.cgutwin.deckedout2.input.Command;
import ca.cgutwin.deckedout2.input.commands.MoveDownCommand;
import ca.cgutwin.deckedout2.input.commands.MoveLeftCommand;
import ca.cgutwin.deckedout2.input.commands.MoveRightCommand;
import ca.cgutwin.deckedout2.input.commands.MoveUpCommand;
import ca.cgutwin.deckedout2.physics.components.PhysicsComponent;
import ca.cgutwin.deckedout2.player.PlayerComponent;
import ca.cgutwin.deckedout2.player.PlayerControlComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerInputProcessor implements InputProcessor
{
  private final Map<Integer, Command> keyCommandMap;
  private final Set<Integer> pressedKeys;
  private final ComponentMapper<PhysicsComponent> physicsMapper;
  private final Engine engine;

  public PlayerInputProcessor(Engine engine) {
    this.engine   = engine;
    physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);

    keyCommandMap = new HashMap<>();
    pressedKeys   = new HashSet<>();

    keyCommandMap.put(Input.Keys.W, new MoveUpCommand());
    keyCommandMap.put(Input.Keys.A, new MoveLeftCommand());
    keyCommandMap.put(Input.Keys.S, new MoveDownCommand());
    keyCommandMap.put(Input.Keys.D, new MoveRightCommand());
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keyCommandMap.containsKey(keycode)) {
      pressedKeys.add(keycode);
      return true;
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    pressedKeys.remove(keycode);
    return true;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(float amountX, float amountY) {
    return false;
  }

  public void update(float deltaTime) {
    ImmutableArray<Entity> family = engine.getEntitiesFor(
            Family.all(PlayerComponent.class, PlayerControlComponent.class, PhysicsComponent.class).get());

    for (Entity entity: family) {
      PhysicsComponent physics = physicsMapper.get(entity);

      for (Integer keycode: pressedKeys) {
        Command command = keyCommandMap.get(keycode);
        command.execute(physics.body);
      }
    }
  }
}
