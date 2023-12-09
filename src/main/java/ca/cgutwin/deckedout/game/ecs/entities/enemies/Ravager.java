package ca.cgutwin.deckedout.game.ecs.entities.enemies;

import ca.cgutwin.deckedout.game.ecs.components.Position;
import ca.cgutwin.deckedout.game.ecs.components.RenderComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ravager extends Enemy {
  public Ravager(Position spawnPosition) {
    super(spawnPosition);

    Texture texture = new Texture(Gdx.files.internal("./tileset/tileset.png"));
    Sprite sprite = new Sprite(texture, 232, 16, 8, 8);

    this.enemy.addComponent(new RenderComponent(sprite));
  }
}
