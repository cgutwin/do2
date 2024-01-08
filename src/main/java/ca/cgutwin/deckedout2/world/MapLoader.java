package ca.cgutwin.deckedout2.world;

import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import ca.cgutwin.deckedout2.rendering.renderers.MapRenderer;
import ca.cgutwin.deckedout2.util.DebugSystem;
import ca.cgutwin.deckedout2.world.components.MapComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The MapLoader class is responsible for loading and processing maps in the game world.
 */
public class MapLoader
{
  private final TmxMapLoader mapLoader;
  private final TiledMapParser mapParser;
  private final Engine engine;
  private final Camera camera;

  /**
   * Constructs a MapLoader instance and immediately loads the specified map.
   *
   * @param engine    The Ashley engine for managing entities.
   * @param camera    The camera for rendering the map.
   * @param world     The physics world for the game.
   * @param pathToMap The path to the Tiled map file to load.
   */
  public MapLoader(Engine engine, Camera camera, World world, String pathToMap) {
    this(engine, camera, world);
    loadMap(pathToMap);
  }

  /**
   * Constructs a MapLoader instance.
   *
   * @param engine The Ashley engine for managing entities.
   * @param camera The camera for rendering the map.
   */
  public MapLoader(Engine engine, Camera camera, World world) {
    this.engine = engine;
    this.camera = camera;
    mapLoader   = new TmxMapLoader();
    mapParser   = new TiledMapParser(engine, world);
  }

  /**
   * Loads a Tiled map from the given path and adds it as an entity to the engine.
   *
   * @param pathToMap The path to the Tiled map file to load.
   */
  public void loadMap(String pathToMap) {
    DebugSystem.getInstance().sout("loading map");

    Entity mapEntity = new Entity();

    // Create a MapComponent and associate it with the loaded map
    MapComponent mapComponent = engine.createComponent(MapComponent.class);
    mapComponent.map = mapLoader.load(pathToMap);
    mapEntity.add(mapComponent);

    // Create a RenderableComponent for rendering the map
    RenderableComponent renderableComponent = new RenderableComponent(new MapRenderer());
    renderableComponent.camera = camera;
    mapEntity.add(renderableComponent);

    // Add the map entity to the engine
    engine.addEntity(mapEntity);

    // Process map layers to create entities for tiles with "entity" property
    mapParser.processMapLayers(mapComponent.map);
  }
}
