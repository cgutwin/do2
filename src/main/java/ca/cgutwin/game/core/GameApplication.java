package ca.cgutwin.game.core;

import ca.cgutwin.game.io.Input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL11.*;

public class GameApplication {
  // Input handler
  private final Input input;

  public GameApplication(long window) {
    input = new Input(window);
  }

  public void update() {
    input.update();
    // Update game state
    // Check for input and handle game logic
    if (input.isKeyPressed(GLFW_KEY_SPACE)) {
      System.out.println("Space bar is pressed");
    }
    // More game logic...
  }

  public void render() {
    // Render game objects
    // For example, clear the window
    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
    // More rendering code...
  }
}
