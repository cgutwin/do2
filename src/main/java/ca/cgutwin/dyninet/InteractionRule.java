package ca.cgutwin.dyninet;

/**
 * Defines the contract for interaction rules between nodes in the game.
 * Interaction rules are central to the dynamics of the game, dictating how
 * entities interact with each other based on their properties and the
 * current game context.
 * <p>
 * Implementing this interface allows for the creation of diverse and complex
 * interactions, making the game world more dynamic and responsive to player actions
 * and environmental changes.
 */
public interface InteractionRule {

  /**
   * Applies this interaction rule between two nodes.
   * <p>
   * This method is called when there is a potential interaction between two nodes
   * (e.g., when they come into proximity or a specific game event occurs). The method
   * defines the logic of the interaction, which can depend on the properties of the nodes
   * involved and the overall game context.
   *
   * @param self    The node on which this rule is defined.
   * @param other   The other node involved in the interaction.
   * @param context The current state of the game, providing context for the interaction.
   */
  void applyInteraction(Node self, Node other, GameContext context);
}
