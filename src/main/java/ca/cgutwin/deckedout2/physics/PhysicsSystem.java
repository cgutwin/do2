package ca.cgutwin.deckedout2.physics;

import ca.cgutwin.deckedout2.physics.components.BodyComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsSystem extends IntervalIteratingSystem
{
  public static final float TIME_STEP = 1/60f; // Update at 60 Hz
  private final ComponentMapper<BodyComponent> bodyMapper;
  private final World world;

  public PhysicsSystem(World world) {
    super(Family.all(BodyComponent.class).get(), TIME_STEP);

    this.world      = world;
    this.bodyMapper = ComponentMapper.getFor(BodyComponent.class);
  }

  @Override
  protected void updateInterval() {
    // Step the physics world
    world.step(TIME_STEP, 6, 2);
  }

  @Override
  protected void processEntity(Entity entity) {
    BodyComponent physicsComponent = bodyMapper.get(entity);
    Body body = physicsComponent.body;
  }
}
