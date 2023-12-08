package ca.cgutwin.deckedout.game.cards;

import ca.cgutwin.deckedout.game.ecs.managers.GameStateManager;

public interface Card {
  void applyEffect(GameStateManager stateManager);
}
