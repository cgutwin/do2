package ca.cgutwin;

import ca.cgutwin.systems.ISystem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ECSManagerTest {
  @Test
  @DisplayName("Initializes with the default systems")
  public void testInit() {
    ECSManager ecsManager = new ECSManager();
    ecsManager.init();
    List<ISystem> systems = ecsManager.getSystems();
    assertFalse(systems.isEmpty());
  }
}