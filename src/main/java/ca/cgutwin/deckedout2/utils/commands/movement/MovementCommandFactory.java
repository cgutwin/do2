/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.utils.commands.movement;

import ca.cgutwin.deckedout2.utils.commands.Command;
import ca.cgutwin.deckedout2.world.Level;

public class MovementCommandFactory {

  public static Command newMovementCommand(MovementEnum direction, Level level) {
    return switch (direction) {
      case UP -> new MovementCommand(0, 1, level);
      case DOWN -> new MovementCommand(0, -1, level);
      case LEFT -> new MovementCommand(-1, 0, level);
      case RIGHT -> new MovementCommand(1, 0, level);
    };
  }
}
