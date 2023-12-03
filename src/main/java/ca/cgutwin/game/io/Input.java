package ca.cgutwin.game.io;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
  private final long window;

  private final boolean[] keys = new boolean[GLFW_KEY_LAST];
  private final boolean[] buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];

  public Input(long window) {
    this.window = window;
  }

  public void update() {
    for (int i = 32; i < GLFW_KEY_LAST; i++) {
      keys[i] = glfwGetKey(window, i) == GLFW_PRESS;
    }

    for (int i = 0; i < GLFW_MOUSE_BUTTON_LAST; i++) {
      buttons[i] = glfwGetMouseButton(window, i) == GLFW_PRESS;
    }
  }

  public boolean isKeyPressed(int keycode) {
    return keys[keycode];
  }

  public boolean isButtonPressed(int button) {
    return buttons[button];
  }
}
