/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.utils;

import com.badlogic.gdx.physics.box2d.BodyDef;

public class BodyUtils {
  private static BodyDef getBodyDef(float x, float y) {
    BodyDef bodyDef = new BodyDef();

    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set(x, y);

    return bodyDef;
  }
}
