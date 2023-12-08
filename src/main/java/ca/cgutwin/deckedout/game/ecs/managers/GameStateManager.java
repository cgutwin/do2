package ca.cgutwin.deckedout.game.ecs.managers;

import ca.cgutwin.deckedout.game.cards.Card;
import ca.cgutwin.deckedout.game.ecs.entities.Entity;
import ca.cgutwin.deckedout.game.ecs.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class GameStateManager {
  private final Player player;
  private final List<Card> activeCards;
  private GameState currentState;

  public GameStateManager() {
    this.player = new Player();
    this.activeCards = new ArrayList<>();
  }

  public Entity getPlayer() {
    return player.getPlayer();
  }

  public void addCard(Card card) {
    activeCards.add(card);
  }
}
