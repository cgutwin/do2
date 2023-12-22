package ca.cgutwin.deckedout.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

import static com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent implements Component, Poolable {
  public Texture texture = null;

  @Override
  public void reset() {
    texture = null;
  }
}