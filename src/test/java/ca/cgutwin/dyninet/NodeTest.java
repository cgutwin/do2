package ca.cgutwin.dyninet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NodeTest {
  private Node node;

  @BeforeEach
  void setUp() {
    node = new Node("testNode");
  }

  @Test
  void testAddProperty() {
    node.addProperty("key", "value");
    assertEquals("value", node.getProperties().get("key"));
  }

  @Test
  void testAddInteractionRule() {
    InteractionRule rule = (self, other, context) -> { /* rule implementation */ };

    node.addInteractionRule(rule);
    assertFalse(node.getInteractionRules().isEmpty());
  }
}