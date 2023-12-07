package ca.cgutwin.ecs.managers;


import ca.cgutwin.core.camera.movement.ICommand;
import com.badlogic.gdx.InputAdapter;

import java.util.HashMap;
import java.util.Map;

public class InputManager extends InputAdapter {
  private static InputManager instance;
  private final Map<Integer, ICommand> keyDownActions;
  private final Map<Integer, ICommand> keyUpActions;

  private InputManager() {
    keyDownActions = new HashMap<>();
    keyUpActions   = new HashMap<>();
  }

  public static InputManager getInstance() {
    if (instance == null) {
      instance = new InputManager();
    }
    return instance;
  }

  public void bindKeyDown(int key, ICommand command) {
    keyDownActions.put(key, command);
  }

  public void bindKeyUp(int key, ICommand command) {
    keyUpActions.put(key, command);
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keyDownActions.containsKey(keycode)) {
      keyDownActions.get(keycode).execute();
      return true;
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keyUpActions.containsKey(keycode)) {
      keyUpActions.get(keycode).execute();
      return true;
    }
    return false;
  }
}
