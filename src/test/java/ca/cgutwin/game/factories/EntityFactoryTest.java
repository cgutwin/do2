package ca.cgutwin.game.factories;

import ca.cgutwin.game.ecs.components.ComponentStub;
import ca.cgutwin.game.ecs.entities.Entity;
import ca.cgutwin.game.ecs.managers.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class EntityFactoryTest {
  @Mock
  private EntityManager entityManager;

  private EntityFactory entityFactory;

  @BeforeEach
  void setUp() {
    entityFactory = new EntityFactory(entityManager);
  }

  @Test
  void createPlayer() {
    Mockito.when(entityManager.createEntity()).thenReturn(new Entity());
    // Use the factory to create a player
    Entity player = entityFactory.createPlayer();

    // Verify that an entity was created
    assertNotNull(player);

    // Verify the entity has expected components
    assertTrue(player.hasComponent(ComponentStub.class));
  }
}