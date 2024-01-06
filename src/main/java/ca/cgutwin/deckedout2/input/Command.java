package ca.cgutwin.deckedout2.input;

import com.badlogic.gdx.physics.box2d.Body;

public interface Command
{
  void execute(Body body);
}
