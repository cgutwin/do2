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

import ca.cgutwin.deckedout2.components.TextureComponent;
import ca.cgutwin.deckedout2.components.TransformationComponent;
import ca.cgutwin.deckedout2.components.b2d.BodyComponent;
import ca.cgutwin.deckedout2.utils.Coordinates;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class TextureRenderer implements EntityRenderer {
  private final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
  private final ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
  private final ComponentMapper<TransformationComponent> positionMapper = ComponentMapper.getFor(
          TransformationComponent.class);

  @Override
  public void render(Entity entity, SpriteBatch spriteBatch) {
    TextureComponent textureComp = textureMapper.get(entity);
    TransformationComponent positionComp = positionMapper.get(entity);
    BodyComponent bodyComp = bodyMapper.get(entity);

    if (textureComp.texture != null) {
      Sprite sprite = new Sprite(textureComp.texture);
      Coordinates coordinates = new Coordinates(bodyComp.body.getPosition());

      sprite.setPosition(coordinates.x(), coordinates.y());
      sprite.setRotation(bodyComp.body.getAngle()*MathUtils.radiansToDegrees);
      sprite.draw(spriteBatch);
    }
  }
}
