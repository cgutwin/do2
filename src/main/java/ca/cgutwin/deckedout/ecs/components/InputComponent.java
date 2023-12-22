package ca.cgutwin.deckedout.ecs.components;

import com.badlogic.ashley.core.Component;

public class InputComponent implements Component {
  public boolean left, right, up, down, jump, sprint; // Add more as needed

  // Reset method if you're using pooling
  public void reset() {
    left = right = up = down = jump = sprint = false;
  }
}
