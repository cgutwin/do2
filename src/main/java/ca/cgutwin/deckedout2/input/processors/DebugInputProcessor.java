package ca.cgutwin.deckedout2.input.processors;

import com.badlogic.gdx.InputProcessor;

public class DebugInputProcessor implements InputProcessor
{
  private final int overlayKey;
  private boolean overlayActive = false;

  public DebugInputProcessor(int overlayKey) {
    this.overlayKey = overlayKey;
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == overlayKey) {
      overlayActive = !overlayActive; // Toggle the overlay
      onOverlayToggle(overlayActive);
      return true; // Indicate that the key was handled
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
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

  private void onOverlayToggle(boolean isActive) {
    System.out.println("[F1] overlay " + (isActive ? "shown" : "hidden"));
    // Handle overlay activation/deactivation logic
    // E.g., pausing the game, showing/hiding the overlay UI
  }
}
