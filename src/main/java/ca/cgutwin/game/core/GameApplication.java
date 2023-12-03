package ca.cgutwin.game.core;


import ca.cgutwin.game.screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends Game {
  private SpriteBatch batch;
  private BitmapFont font;

  @Override
  public void create() {
    batch = new SpriteBatch();
    font  = new BitmapFont();

    // Set the initial screen, typically the main menu
    this.setScreen(new MenuScreen(this));
  }

  @Override
  public void dispose() {
    batch.dispose();
    font.dispose();
  }

  @Override
  public void render() {
    super.render(); // Important! This will render the active screen.
  }

  public SpriteBatch getBatch() {
    return batch;
  }


  public BitmapFont getFont() {
    return font;
  }
}
