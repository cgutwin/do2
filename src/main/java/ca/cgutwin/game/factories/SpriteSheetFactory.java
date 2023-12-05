package ca.cgutwin.game.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class SpriteSheetFactory {
  private final Map<String, Texture> spriteSheets;

  public SpriteSheetFactory() {
    spriteSheets = new HashMap<>();
  }

  public void loadSpriteSheet(String key, String filePath) {
    spriteSheets.put(key, new Texture(filePath));
  }

  public TextureRegion getTextureRegion(String sheetKey, int x, int y, int tileWidth, int tileHeight) {
    Texture spriteSheet = spriteSheets.get(sheetKey);
    if (spriteSheet != null) {
      return new TextureRegion(spriteSheet, x, y, tileWidth, tileHeight);
    }
    return null;
  }

  public void dispose() {
    for (Texture texture: spriteSheets.values()) {
      texture.dispose();
    }
    spriteSheets.clear();
  }
}
