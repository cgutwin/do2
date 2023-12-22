package ca.cgutwin.deckedout.ecs.systems;

import ca.cgutwin.deckedout.ecs.components.TextureComponent;
import ca.cgutwin.deckedout.ecs.components.physics.PositionComponent;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class RenderSystem extends EntitySystem {
  private final SpriteBatch batch;
  private final ComponentMapper<TextureComponent> textureM;
  private final ComponentMapper<PositionComponent> positionM;
  private ImmutableArray<Entity> entities;

  public RenderSystem(SpriteBatch batch) {
    textureM   = ComponentMapper.getFor(TextureComponent.class);
    positionM  = ComponentMapper.getFor(PositionComponent.class);
    this.batch = batch;
  }

  @Override
  public void addedToEngine(Engine engine) {
    entities = engine.getEntitiesFor(Family.all(TextureComponent.class, PositionComponent.class).get());
  }

  @Override
  public void update(float dt) {
    ScreenUtils.clear(0, 0, 0, 1);

    for (Entity entity: entities) {
      TextureComponent tex = textureM.get(entity);
      PositionComponent pos = positionM.get(entity);

      if (tex.texture != null) {
        batch.begin();
        batch.draw(tex.texture, pos.position.x, pos.position.y);
        batch.end();
      }
    }
  }
}
