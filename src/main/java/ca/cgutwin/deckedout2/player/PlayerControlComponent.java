package ca.cgutwin.deckedout2.player;

import ca.cgutwin.deckedout2.input.*;
import com.badlogic.ashley.core.Component;

public class PlayerControlComponent implements Component
{
  public Command leftCommand;
  public Command rightCommand;
  public Command upCommand;
  public Command downCommand;

  public PlayerControlComponent() {
    this.leftCommand  = new MoveLeftCommand();
    this.rightCommand = new MoveRightCommand();
    this.upCommand    = new MoveUpCommand();
    this.downCommand  = new MoveDownCommand();
  }
}
