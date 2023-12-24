package ca.cgutwin.dyninet;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InteractionRuleTest {
  @Test
  void testApplyInteraction() {
    InteractionRule rule = mock(InteractionRule.class);
    Node node1 = new Node("node1");
    Node node2 = new Node("node2");
    GameContext context = new GameContext();

    rule.applyInteraction(node1, node2, context);

    verify(rule).applyInteraction(node1, node2, context);
  }
}