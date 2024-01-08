package ca.cgutwin.deckedout2.world;

import ca.cgutwin.deckedout2.physics.collisions.handlers.DefaultCollisionHandler;
import ca.cgutwin.deckedout2.physics.components.CollisionComponent;
import ca.cgutwin.deckedout2.physics.components.PhysicsComponent;
import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import ca.cgutwin.deckedout2.rendering.components.TextureComponent;
import ca.cgutwin.deckedout2.rendering.renderers.TileEntityRenderer;
import ca.cgutwin.deckedout2.util.DebugSystem;
import ca.cgutwin.deckedout2.world.components.TileComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.World;

public class TiledMapParser
{
  private final Engine engine;
  private final MapBodyBuilder mapBodyBuilder;

  public TiledMapParser(Engine engine, World world) {
    this.engine         = engine;
    this.mapBodyBuilder = new MapBodyBuilder(world, 32);
  }


  /**
   * Processes the layers of the Tiled map to create entities for tiles with the "entity" property.
   *
   * @param map The Tiled map to process.
   */
  public void processMapLayers(TiledMap map) {
    for (MapLayer layer: map.getLayers()) {
      if (layer instanceof TiledMapTileLayer) {
        processTileLayer((TiledMapTileLayer) layer);
      }
    }
  }

  /**
   * Processes a specific tile layer to create entities for tiles with the "entity" property.
   *
   * @param layer The Tiled map tile layer to process.
   */
  private void processTileLayer(TiledMapTileLayer layer) {
    for (int y = 0; y < layer.getHeight(); y++) {
      for (int x = 0; x < layer.getWidth(); x++) {
        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
        if (cell != null) {
          if (shouldCreateEntityForCell(cell)) {
            Entity entity = createEntityForCell(cell, x, y);

            if (shouldCreateBodyForCell(cell)) {

              entity.getComponent(PhysicsComponent.class).body = mapBodyBuilder.createBody(cell, x, y);
            }
          }
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
    return cell.getTile().getProperties().containsKey("entity") || cell.getTile().getObjects().getCount() == 1;
  }

  /**
   * Creates an entity for a specific tile cell.
   *
   * @param cell The Tiled map tile cell for which to create an entity.
   * @param x    The x-coordinate of the cell.
   * @param y    The y-coordinate of the cell.
   */
  private Entity createEntityForCell(TiledMapTileLayer.Cell cell, int x, int y) {
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
    tileEntity.add(new PhysicsComponent());


    if (TileTypeEnum.valueOf(tileType) == TileTypeEnum.WALL) {
      tileEntity.add(new CollisionComponent(new DefaultCollisionHandler()));
    }

    engine.addEntity(tileEntity);

    DebugSystem.getInstance().sout("added entity: " + tileEntity);

    return tileEntity;
  }

  /**
   * Checks if a tile cell should create a physics body.
   *
   * @param cell The Tiled map tile cell to check.
   * @return True if a body should be created for the cell, false otherwise.
   */
  private boolean shouldCreateBodyForCell(TiledMapTileLayer.Cell cell) {
    return cell.getTile().getObjects().getCount() == 1;
  }
}
