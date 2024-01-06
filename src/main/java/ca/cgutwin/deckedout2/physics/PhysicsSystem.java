package ca.cgutwin.deckedout2.physics;

import ca.cgutwin.deckedout2.physics.components.PhysicsComponent;
import ca.cgutwin.deckedout2.player.TransformComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsSystem extends IntervalIteratingSystem
{
  public static final float TIME_STEP = 1f/60f; // Update at 60 Hz
  private final ComponentMapper<PhysicsComponent> physicsMapper;
  private final ComponentMapper<TransformComponent> transformMapper;
  private final World world;

  public PhysicsSystem(World world) {
    super(Family.all(PhysicsComponent.class).get(), TIME_STEP);

    this.world           = world;
    this.physicsMapper   = ComponentMapper.getFor(PhysicsComponent.class);
    this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
  }

  @Override
  protected void processEntity(Entity entity) {
    world.step(TIME_STEP, 6, 2);
    synchronizePhysicsAndGDXPositioning(entity);
  }

  private void synchronizePhysicsAndGDXPositioning(Entity entity) {
    PhysicsComponent physicsComp = physicsMapper.get(entity);
    TransformComponent transformComp = transformMapper.get(entity);

    if (physicsComp.body != null) {
      Vector2 bodyPos = physicsComp.body.getPosition();
      transformComp.position.setFromVector2(bodyPos);
    }
  }
}
