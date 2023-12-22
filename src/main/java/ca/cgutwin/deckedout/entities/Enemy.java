package ca.cgutwin.deckedout.entities;

import ca.cgutwin.deckedout.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Enemy extends Entity {
  Texture texture;

  public Enemy(Texture texture) {
    this.texture = texture;
  }

  public abstract void pathfind();

  public abstract void draw(SpriteBatch sb);
}
