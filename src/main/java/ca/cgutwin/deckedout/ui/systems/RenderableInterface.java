package ca.cgutwin.deckedout.ui.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface RenderableInterface {
  void render(SpriteBatch sb);

  void update(float dt);

  void dispose();
}
