package ca.cgutwin.deckedout2.world;

import ca.cgutwin.deckedout2.physics.collisions.handlers.DefaultCollisionHandler;
import ca.cgutwin.deckedout2.physics.components.CollisionComponent;
import ca.cgutwin.deckedout2.rendering.RenderComponent;
import ca.cgutwin.deckedout2.util.DebugSystem;
import ca.cgutwin.deckedout2.world.components.TileComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader
{
  private final TmxMapLoader mapLoader;
  private final Engine engine;
  private TiledMap map;

  public MapLoader(Engine engine, String pathToMap) {
    this(engine);
    loadMap(pathToMap);
  }

  public MapLoader(Engine engine) {
    this.engine = engine;
    mapLoader   = new TmxMapLoader();
  }

  public void loadMap(String pathToMap) {
    DebugSystem.getInstance().sout("loading map");

    map = mapLoader.load(pathToMap);

    if (map != null) { DebugSystem.getInstance().sout("loaded " + map); }

    assert map != null;
    processMapLayers(map);
  }

  private void processMapLayers(TiledMap map) {
    for (MapLayer layer: map.getLayers()) {
      if (layer instanceof TiledMapTileLayer) {
        processTileLayer((TiledMapTileLayer) layer);
      }
    }
  }

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

  private boolean shouldCreateEntityForCell(TiledMapTileLayer.Cell cell) {
    return cell.getTile().getProperties().containsKey("entity");
  }

  private void createEntityForCell(TiledMapTileLayer.Cell cell, int x, int y) {
    Entity tileEntity = new Entity();

    MapProperties properties = cell.getTile().getProperties();
    String tileType = properties.get("type", String.class);

    TileComponent tileComponent = new TileComponent(TileTypeEnum.valueOf(tileType), new Position(x, y));
    RenderComponent renderComponent = new RenderComponent(cell.getTile().getTextureRegion());
    CollisionComponent collisionComponent = new CollisionComponent(new DefaultCollisionHandler());

    tileEntity.add(tileComponent);
    tileEntity.add(renderComponent);
    tileEntity.add(collisionComponent);

    engine.addEntity(tileEntity);
    DebugSystem.getInstance().sout("added entity: " + tileEntity);
  }

  public TiledMap map() {
    return map;
  }
}
