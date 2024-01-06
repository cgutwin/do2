package ca.cgutwin.deckedout2.world.components;

import ca.cgutwin.deckedout2.world.Position;
import ca.cgutwin.deckedout2.world.TileTypeEnum;
import com.badlogic.ashley.core.Component;

public class TileComponent implements Component
{
  public TileTypeEnum tileType;
  public Position position;

  public TileComponent(TileTypeEnum tileType, Position position) {
    this.tileType = tileType;
    this.position = position;
  }
}
