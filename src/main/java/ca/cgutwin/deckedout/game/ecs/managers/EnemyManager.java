package ca.cgutwin.deckedout.game.ecs.managers;

import ca.cgutwin.deckedout.game.ecs.components.Position;
import ca.cgutwin.deckedout.game.ecs.entities.enemies.Enemy;
import ca.cgutwin.deckedout.game.ecs.entities.enemies.Ravager;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
  private final List<Enemy> enemies;

  public EnemyManager() {
    this.enemies = new ArrayList<>();
  }

  public void update(float deltaTime) {

  }

  // TODO: Enemy factory
  public void spawnEnemy() {
    Position spawnPosition = calculateSpawnPosition();
    Enemy newEnemy = new Ravager(spawnPosition); // Example health value
    enemies.add(newEnemy);
  }

  private Position calculateSpawnPosition() {
    // Calculate and return a new spawn position
    // This could be random or based on specific rules
    return new Position(20*8, 20*8); // Placeholder
  }
}
