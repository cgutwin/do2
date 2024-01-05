/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.components;

import ca.cgutwin.deckedout2.utils.commands.Command;
import com.badlogic.ashley.core.Component;

import java.util.HashMap;
import java.util.Map;

public class InputComponent implements Component {
  public Map<Integer, Command> keyCommands = new HashMap<>();
}
