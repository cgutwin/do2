package ca.cgutwin.deckedout2.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapRenderer extends OrthogonalTiledMapRenderer
{
  public MapRenderer(TiledMap map) {
    super(map, 32);
  }

  @Override
  public void renderTileLayer(TiledMapTileLayer layer) {
    for (int x = 0; x < layer.getWidth(); x++) {
      for (int y = 0; y < layer.getHeight(); y++) {

        TiledMapTileLayer.Cell cell = layer.getCell(x, y);

        if (cell != null && cell.getTile() != null) {

          Boolean isEntity = cell.getTile().getProperties().get("entity", Boolean.class);

          if (isEntity == null || !isEntity) {
            batch.draw(cell.getTile().getTextureRegion(), x*getUnitScale(), y*getUnitScale());
          }
        }
      }
    }
  }
}
