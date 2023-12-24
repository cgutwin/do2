/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {
  private Texture texture;

  public TextureComponent(Texture texture) {
    this.texture = texture;
  }

  public Texture texture() {
    return texture;
  }

  public void setTexture(Texture texture) {
    this.texture = texture;
  }
}
