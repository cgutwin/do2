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

public class MapLoader
{
  private final TmxMapLoader mapLoader;
  private final Engine engine;
  private final Camera camera;

  public MapLoader(Engine engine, Camera camera, String pathToMap) {
    this(engine, camera);
    loadMap(pathToMap);
  }

  public MapLoader(Engine engine, Camera camera) {
    this.engine = engine;
    this.camera = camera;
    mapLoader   = new TmxMapLoader();
  }

  public void loadMap(String pathToMap) {
    DebugSystem.getInstance().sout("loading map");

    Entity mapEntity = new Entity();

    MapComponent mapComponent = engine.createComponent(MapComponent.class);
    mapComponent.map = mapLoader.load(pathToMap);
    mapEntity.add(mapComponent);
    RenderableComponent renderableComponent = new RenderableComponent(new MapRenderer());
    renderableComponent.camera = camera;
    mapEntity.add(renderableComponent);

    engine.addEntity(mapEntity);

    processMapLayers(mapComponent.map);
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
    TextureComponent textureComponent = new TextureComponent(cell.getTile().getTextureRegion());

    tileEntity.add(new RenderableComponent(new TileEntityRenderer()));
    tileEntity.add(tileComponent);
    tileEntity.add(textureComponent);

    engine.addEntity(tileEntity);
    DebugSystem.getInstance().sout("added entity: " + tileEntity);
  }
}
