package ca.cgutwin.game.core.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ITile {
  void render(SpriteBatch batch);
  void update(float delta);
  boolean isWalkable();
}
