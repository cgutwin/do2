package ca.cgutwin.deckedout2.rendering;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The StaticTileMapRenderer class extends OrthogonalTiledMapRenderer and is used to render a TiledMap with static
 * tiles.
 * It filters out and does not render tiles with the "entity" property set to true.
 */
public class StaticTileMapRenderer extends OrthogonalTiledMapRenderer
{
  /**
   * Constructs a StaticTileMapRenderer with no initial map.
   */
  public StaticTileMapRenderer() {
    this(null);
  }

  /**
   * Constructs a StaticTileMapRenderer with the specified TiledMap and unit scale.
   *
   * @param map The TiledMap to render.
   */
  public StaticTileMapRenderer(TiledMap map) {
    super(map, 32); // Use a unit scale of 32
  }

  /**
   * Renders a specific tile layer, excluding tiles with the "entity" property set to true.
   *
   * @param layer The TiledMapTileLayer to render.
   */
  @Override
  public void renderTileLayer(TiledMapTileLayer layer) {
    for (int x = 0; x < layer.getWidth(); x++) {
      for (int y = 0; y < layer.getHeight(); y++) {

        TiledMapTileLayer.Cell cell = layer.getCell(x, y);

        if (cell != null && cell.getTile() != null) {

          // Check if the tile has the "entity" property set to true
          Boolean isEntity = cell.getTile().getProperties().get("entity", Boolean.class);

          // If it's not an entity (or is null), render the tile
          if (isEntity == null || !isEntity) {
            batch.draw(cell.getTile().getTextureRegion(), x*getUnitScale(), y*getUnitScale());
          }
        }
      }
    }
  }
}