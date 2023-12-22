package ca.cgutwin.deckedout;

import ca.cgutwin.deckedout.core.WorldManager;
import ca.cgutwin.deckedout.ecs.entities.Player;
import ca.cgutwin.deckedout.factories.Box2DBodyFactoryImpl;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainGame extends Game {
  private final Box2DBodyFactoryImpl bodyFactory;
  private OrthographicCamera camera;
  private final WorldManager worldManager;
  private Player player;

  public MainGame() {
    worldManager = new WorldManager();
    bodyFactory  = new Box2DBodyFactoryImpl(worldManager.world());
  }

  @Override
  public void create() {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
    player = new Player(bodyFactory, "img_1.png", 2, 2);


    // Start with the initial level
    setScreen(new PlayScreen(this));
  }
}
