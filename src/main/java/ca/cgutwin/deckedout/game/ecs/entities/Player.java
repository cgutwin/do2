package ca.cgutwin.deckedout.game.ecs.entities;

import ca.cgutwin.deckedout.game.ecs.components.MovementComponent;
import ca.cgutwin.deckedout.game.ecs.components.PositionComponent;
import ca.cgutwin.deckedout.game.ecs.components.RenderComponent;
import ca.cgutwin.deckedout.game.ecs.managers.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Entity {
  private Entity player;
  private EntityManager entityManager = EntityManager.getInstance();

  private final PositionComponent positionComponent;
  private final MovementComponent movementComponent;

  public Player() {
    this.player = entityManager.createEntity();

    Texture texture = new Texture(Gdx.files.internal("./tileset/tileset.png"));
    Sprite sprite = new Sprite(texture, 40, 224, 8, 8);

    this.positionComponent = new PositionComponent(24, 22*8);
    this.movementComponent = new MovementComponent(50);

    this.player.addComponent(positionComponent);
    this.player.addComponent(movementComponent);
    this.player.addComponent(new RenderComponent(sprite));
  }

  public Entity getPlayer() {
    return player;
  }
}
