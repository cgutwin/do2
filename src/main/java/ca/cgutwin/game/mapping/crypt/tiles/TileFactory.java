package ca.cgutwin.game.mapping.crypt.tiles;

import ca.cgutwin.game.core.tiles.Tile;
import ca.cgutwin.game.factories.SpriteSheetFactory;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileFactory {
  private final SpriteSheetFactory spriteSheetFactory;

  public TileFactory(SpriteSheetFactory spriteSheetFactory) {
    this.spriteSheetFactory = spriteSheetFactory;
  }

  public Tile createTile(String type) {
    TextureRegion texture;

    switch (type) {
      case "Snow":
        texture = spriteSheetFactory.getTextureRegion("snowSheet", 128, 96, 8, 8);
        return new SnowTile(texture);
      default:
        return null;
    }
  }
}