package ca.cgutwin.deckedout.game.ecs.systems;

import ca.cgutwin.deckedout.game.ecs.components.PositionComponent;
import ca.cgutwin.deckedout.game.ecs.components.RenderComponent;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.managers.EntityManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem implements System {
  private final EntityManager entityManager = EntityManager.getInstance();
  private final SpriteBatch spriteBatch;
  private final OrthographicCamera camera;

  public RenderSystem(SpriteBatch spriteBatch, OrthographicCamera camera) {
    this.spriteBatch = spriteBatch;
    this.camera      = camera;
  }

  @Override
  public void update(float deltaTime) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    for (Entity entity: entityManager.getAllEntities()) {
      if (entity.hasComponent(RenderComponent.class) && entity.hasComponent(PositionComponent.class)) {
        RenderComponent render = entity.getComponent(RenderComponent.class);
        PositionComponent position = entity.getComponent(PositionComponent.class);

        spriteBatch.draw(render.textureRegion, position.x, position.y);
      }
    }
    spriteBatch.end();
  }
}
