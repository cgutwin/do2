package ca.cgutwin.deckedout2.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * The MapComponent class represents a component that holds a reference to a TiledMap.
 * It is used to associate a TiledMap with an entity in the Ashley framework.
 */
public class MapComponent implements Component
{
  /**
   * The TiledMap associated with this component.
   */
  public TiledMap map;
}
