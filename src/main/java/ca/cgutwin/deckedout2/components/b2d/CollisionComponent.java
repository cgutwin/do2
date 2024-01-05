/*
 * Copyright (c) 2024 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.components.b2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/*
 * Stores collision data such as entity that this entity has collided with
 */
public class CollisionComponent implements Component {
  public Entity collisionEntity;
}
