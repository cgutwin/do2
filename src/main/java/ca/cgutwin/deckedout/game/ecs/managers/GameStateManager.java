package ca.cgutwin.deckedout.game.ecs.managers;

import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.entities.Player;

public class GameStateManager {
  private EntityManager entityManager = EntityManager.getInstance();
  private Player player;
  private GameState currentState;

  public GameStateManager() {
    this.player = new Player();
  }

  public Entity getPlayer() {
    return player.getPlayer();
  }
}
