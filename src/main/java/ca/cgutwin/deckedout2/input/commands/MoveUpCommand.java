package ca.cgutwin.deckedout2.input.commands;

import ca.cgutwin.deckedout2.input.Command;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class MoveUpCommand implements Command
{
  @Override
  public void execute(Body body) {
    Vector2 position = body.getPosition();
    body.applyLinearImpulse(new Vector2(0f, 5f), position, true);
  }
}
