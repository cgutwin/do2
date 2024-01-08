package ca.cgutwin.deckedout2.input.processors;

import ca.cgutwin.deckedout2.util.debug.DebugPanel;
import com.badlogic.gdx.InputProcessor;

public class DebugInputProcessor implements InputProcessor
{
  private final int overlayKey;
  private final DebugPanel debugPanel;
  private boolean overlayActive = false;


  public DebugInputProcessor(int overlayKey, DebugPanel debugPanel) {
    this.overlayKey = overlayKey;
    this.debugPanel = debugPanel;
  }

  public boolean overlayActive() {
    return overlayActive;
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
    if (overlayActive) { return debugPanel.stage().touchDown(screenX, screenY, pointer, button); }
    else { return false; }
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    if (overlayActive) { return debugPanel.stage().touchUp(screenX, screenY, pointer, button); }
    else { return false; }
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
    if (overlayActive) { return debugPanel.stage().scrolled(amountX, amountY); }
    else { return false; }
  }

  private void onOverlayToggle(boolean isActive) {
    System.out.println("[F1] overlay " + (isActive ? "shown" : "hidden"));
    if (isActive) {
      debugPanel.show(); // Method to show the debug panel
    }
    else {
      debugPanel.hide(); // Method to hide the debug panel
    }
  }
}
