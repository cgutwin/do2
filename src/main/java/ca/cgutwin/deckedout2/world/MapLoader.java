package ca.cgutwin.deckedout2.world;

import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import ca.cgutwin.deckedout2.rendering.components.TextureComponent;
import ca.cgutwin.deckedout2.rendering.renderers.MapRenderer;
import ca.cgutwin.deckedout2.rendering.renderers.TileEntityRenderer;
import ca.cgutwin.deckedout2.util.DebugSystem;
import ca.cgutwin.deckedout2.world.components.MapComponent;
import ca.cgutwin.deckedout2.world.components.TileComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * The MapLoader class is responsible for loading and processing maps in the game world.
 */
public class MapLoader
{
  private final TmxMapLoader mapLoader;
  private final Engine engine;
  private final Camera camera;

  /**
   * Constructs a MapLoader instance and immediately loads the specified map.
   *
   * @param engine    The Ashley engine for managing entities.
   * @param camera    The camera for rendering the map.
   * @param pathToMap The path to the Tiled map file to load.
   */
  public MapLoader(Engine engine, Camera camera, String pathToMap) {
    this(engine, camera);
    loadMap(pathToMap);
  }

  /**
   * Constructs a MapLoader instance.
   *
   * @param engine The Ashley engine for managing entities.
   * @param camera The camera for rendering the map.
   */
  public MapLoader(Engine engine, Camera camera) {
    this.engine = engine;
    this.camera = camera;
    mapLoader   = new TmxMapLoader();
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
    processMapLayers(mapComponent.map);
  }

  /**
   * Processes the layers of the Tiled map to create entities for tiles with the "entity" property.
   *
   * @param map The Tiled map to process.
   */
  private void processMapLayers(TiledMap map) {
    for (MapLayer layer: map.getLayers()) {
      if (layer instanceof TiledMapTileLayer) {
        processTileLayer((TiledMapTileLayer) layer);
      }
    }
  }

  /**
   * Processes a specific tile layer to create entities for tiles with the "entity" property.
   *
   * @param tileLayer The Tiled map tile layer to process.
   */
  private void processTileLayer(TiledMapTileLayer tileLayer) {
    for (int y = 0; y < tileLayer.getHeight(); y++) {
      for (int x = 0; x < tileLayer.getWidth(); x++) {
        TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
        if (cell != null && shouldCreateEntityForCell(cell)) {
          createEntityForCell(cell, x, y);
        }
      }
    }
  }

  /**
   * Checks if a tile cell should create an entity based on the "entity" property.
   *
   * @param cell The Tiled map tile cell to check.
   * @return True if an entity should be created for the cell, false otherwise.
   */
  private boolean shouldCreateEntityForCell(TiledMapTileLayer.Cell cell) {
    return cell.getTile().getProperties().containsKey("entity");
  }

  /**
   * Creates an entity for a specific tile cell.
   *
   * @param cell The Tiled map tile cell for which to create an entity.
   * @param x    The x-coordinate of the cell.
   * @param y    The y-coordinate of the cell.
   */
  private void createEntityForCell(TiledMapTileLayer.Cell cell, int x, int y) {
    Entity tileEntity = new Entity();

    // Get tile properties and type
    MapProperties properties = cell.getTile().getProperties();
    String tileType = properties.get("type", String.class);

    // Create TileComponent and TextureComponent for the entity
    TileComponent tileComponent = new TileComponent(TileTypeEnum.valueOf(tileType), new Position(x, y));
    TextureComponent textureComponent = new TextureComponent(cell.getTile().getTextureRegion());

    // Add components to the entity and add it to the engine
    tileEntity.add(new RenderableComponent(new TileEntityRenderer()));
    tileEntity.add(tileComponent);
    tileEntity.add(textureComponent);
    engine.addEntity(tileEntity);

    DebugSystem.getInstance().sout("added entity: " + tileEntity);
  }
}
