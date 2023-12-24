/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.dyninet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NodeInteractionTest {
  private Node node1;
  private Node node2;
  private GameContext context;

  @BeforeEach
  void setUp() {
    // Initialize nodes and a mock game context
    node1   = new Node("node1");
    node2   = new Node("node2");
    context = Mockito.mock(GameContext.class);

    // Set up a mock interaction rule for testing
    InteractionRule mockRule = Mockito.mock(InteractionRule.class);
    node1.addInteractionRule(mockRule);
  }

  @Test
  void testInteractionRuleExecution() {
    // Simulate an interaction
    node1.getInteractionRules().forEach(rule -> rule.applyInteraction(node1, node2, context));

    // Verify that the interaction rule was applied with the correct parameters
    Mockito.verify(node1.getInteractionRules().getFirst()).applyInteraction(node1, node2, context);
  }
}
