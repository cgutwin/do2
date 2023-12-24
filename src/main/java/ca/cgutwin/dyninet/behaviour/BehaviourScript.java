/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.dyninet.behaviour;

import ca.cgutwin.dyninet.GameContext;
import ca.cgutwin.dyninet.Node;

/**
 * Represents a behavior script that can be attached to nodes. This interface allows for the dynamic modification
 * of node behavior based on the current game context and interactions with other nodes.
 * <p>
 * By implementing this interface, developers can create custom scripts that define how a node reacts or changes
 * in response to various events and conditions in the game. This approach enhances the flexibility and
 * dynamism of node behavior, contributing to a more interactive and engaging game environment.
 */
interface BehaviourScript {

  /**
   * Executes the behavior script for a node.
   * <p>
   * This method is invoked to carry out the specific behavior defined by the script. It can be triggered
   * by various events or conditions in the game, such as a change in the node's state, an interaction with
   * another node, or changes in the global game context.
   *
   * @param node    The node on which the behavior script is to be executed.
   * @param context The current game context, providing global information about the game's state.
   */
  void execute(Node node, GameContext context);
}
