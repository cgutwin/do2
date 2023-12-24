/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.deckedout2.systems.rendering;

import ca.cgutwin.deckedout2.components.PositionComponent;
import ca.cgutwin.deckedout2.components.TextureComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRenderer implements EntityRenderer {
  private final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
  private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);

  @Override
  public void render(Entity entity, SpriteBatch spriteBatch) {
    TextureComponent textureComp = textureMapper.get(entity);
    PositionComponent positionComp = positionMapper.get(entity);

    if (textureComp.texture() != null) {
      spriteBatch.draw(new TextureRegion(textureComp.texture()), positionComp.position().x, positionComp.position().y);
    }
  }
}
