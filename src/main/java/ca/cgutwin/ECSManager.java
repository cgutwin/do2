package ca.cgutwin;

import ca.cgutwin.cards.Card;
import ca.cgutwin.cards.parser.CardParser;
import ca.cgutwin.components.ComponentManager;
import ca.cgutwin.components.DeckComponent;
import ca.cgutwin.entities.Entity;
import ca.cgutwin.entities.EntityManager;
import ca.cgutwin.systems.CardSystem;
import ca.cgutwin.systems.ISystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ECSManager implements IManager {
  private final List<ISystem> systems = new ArrayList<>();
  private final EntityManager entityManager = new EntityManager();
  private final ComponentManager componentManager = new ComponentManager();

  public ECSManager() { }

  public ComponentManager getComponentManager() {
    return componentManager;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

  @Override
  public void init() {
    CardSystem cardSystem = new CardSystem(componentManager);
    CardParser cardParser = new CardParser();
    systems.add(cardSystem);

    Entity playerEntity = entityManager.createPlayer();
    List<Card> cardList;
    try {
      cardList = cardParser.parseAllCardsInDirectory("resources/cards/new");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    componentManager.addComponent(playerEntity, new DeckComponent(cardList));
  }

  @Override
  public void update(float dt) {
    systems.forEach(system -> system.update(dt));
  }

  public List<ISystem> getSystems() {
    return systems;
  }
}