package ca.cgutwin.deckedout2.physics;

import ca.cgutwin.deckedout2.physics.components.BodyComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class PhysicsSystem extends IntervalIteratingSystem
{
  public static final float PHYSICS_STEP_TIME = 1/60f;
  private final World world;
  ArrayList<Entity> entityQueue;

  public PhysicsSystem(World world, float interval) {
    super(Family.all(BodyComponent.class).get(), interval);

    this.world       = world;
    this.entityQueue = new ArrayList<>();
  }

  @Override
  protected void processEntity(Entity entity) {
    System.out.println("entity = " + entity);
  }
}
