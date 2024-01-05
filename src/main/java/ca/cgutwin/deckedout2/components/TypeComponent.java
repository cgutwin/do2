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

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
  public EntityType type = EntityType.OTHER;

  public enum EntityType {
    PLAYER, ENEMY, SCENERY, OTHER
  }
}