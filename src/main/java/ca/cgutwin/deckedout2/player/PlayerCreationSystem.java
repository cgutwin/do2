package ca.cgutwin.deckedout2.player;

import ca.cgutwin.deckedout2.physics.collisions.handlers.DefaultCollisionHandler;
import ca.cgutwin.deckedout2.physics.components.CollisionComponent;
import ca.cgutwin.deckedout2.physics.components.PhysicsComponent;
import ca.cgutwin.deckedout2.rendering.components.RenderableComponent;
import ca.cgutwin.deckedout2.rendering.components.TextureComponent;
import ca.cgutwin.deckedout2.rendering.renderers.PlayerRenderer;
import ca.cgutwin.deckedout2.world.Position;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerCreationSystem extends EntitySystem
{
  private final World world;
  private boolean playerCreated = false;

  public PlayerCreationSystem(World world) {
    this.world = world;
  }

  @Override
  public void update(float deltaTime) {
    if (!playerCreated) {
      createPlayerEntity();
      playerCreated = true;
    }
  }

  private void createPlayerEntity() {
    Entity playerEntity = getEngine().createEntity();
    Position position = new Position(2, 2);

    // Add TransformComponent with initial position
    playerEntity.add(new TransformComponent(position));  // Use initial x, y position

    // Add TextureComponent with player texture
    TextureComponent textureComponent = new TextureComponent(
            new TextureRegion(new Texture(Gdx.files.internal("img_1.png"))));
    playerEntity.add(textureComponent);

    playerEntity.add(new PlayerComponent());

    // Add RenderableComponent with PlayerRenderer
    playerEntity.add(new RenderableComponent(new PlayerRenderer()));

    // Add PlayerControlComponent for player input
    playerEntity.add(new PlayerControlComponent());

    // Add PhysicsComponent with Box2D body
    PhysicsComponent physicsComponent = new PhysicsComponent();
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(position.toVector2()); // Set the starting position

    physicsComponent.body = world.createBody(bodyDef);
    physicsComponent.body.setUserData(playerEntity);
    physicsComponent.body.setLinearDamping(5);
    // Initialize Box2D body for physicsComponent
    playerEntity.add(physicsComponent);

    playerEntity.add(new CollisionComponent(new DefaultCollisionHandler()));

    getEngine().addEntity(playerEntity);
  }
}
