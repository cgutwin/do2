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

import ca.cgutwin.deckedout2.utils.Coordinates;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformationComponent implements Component {
  public final Vector2 scale = new Vector2(16.0f, 16.0f);
  public Coordinates position = new Coordinates();
  public float rotation = 0.0f;
  public boolean isHidden = false;
}
