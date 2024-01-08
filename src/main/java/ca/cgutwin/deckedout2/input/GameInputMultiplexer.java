package ca.cgutwin.deckedout2.input;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.List;

public class GameInputMultiplexer implements InputProcessor
{
  private final List<InputProcessor> processors = new ArrayList<>();

  public void addProcessor(InputProcessor processor) {
    processors.add(processor);
  }

  public boolean keyDown(int keycode) {
    for (InputProcessor processor: processors) {
      if (processor.keyDown(keycode)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    for (InputProcessor processor: processors) {
      if (processor.keyUp(keycode)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    for (InputProcessor processor: processors) {
      if (processor.touchDown(screenX, screenY, pointer, button)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    for (InputProcessor processor: processors) {
      if (processor.touchUp(screenX, screenY, pointer, button)) {
        return true;
      }
    }
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
    for (InputProcessor processor: processors) {
      if (processor.scrolled(amountX, amountY)) {
        return true;
      }
    }
    return false;
  }
}
