package ca.cgutwin;

import ca.cgutwin.cards.Card;
import ca.cgutwin.components.DeckComponent;
import ca.cgutwin.components.HandComponent;
import ca.cgutwin.components.IComponent;
import ca.cgutwin.entities.Entity;
import ca.cgutwin.systems.CardSystem;

import java.util.List;

public class Debug {
  public static void main(String[] args) {
    ECSManager ecsManager = new ECSManager();
    ecsManager.init();


    Entity player = ecsManager.getEntityManager().getPlayer();
    List<IComponent> allComponents = ecsManager.getComponentManager().getAllComponents(player);

    System.out.println(player);
    for (IComponent component: allComponents) {
      System.out.println(component);
    }

    DeckComponent deckComponent = ecsManager.getComponentManager().getComponent(player, DeckComponent.class);
    System.out.println("after load deck:");
    for (Card card: deckComponent.getCards()) {
      System.out.println(card.getName().getLongName());
    }

    ecsManager.getComponentManager().addComponent(player, new HandComponent());

    CardSystem cardSystem = (CardSystem) ecsManager.getSystems().getFirst();
    cardSystem.initCardSystem(player);
    HandComponent hand = ecsManager.getComponentManager().getComponent(player, HandComponent.class);
    System.out.println(hand.getHand().size());

    while (!hand.getHand().isEmpty()) {
      System.out.println(hand.draw());
    }
  }
}
