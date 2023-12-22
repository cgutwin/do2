package ca.cgutwin.deckedout.ecs.entities;

import ca.cgutwin.deckedout.ecs.components.InputComponent;
import ca.cgutwin.deckedout.ecs.components.TextureComponent;
import ca.cgutwin.deckedout.ecs.components.physics.Box2DComponent;
import ca.cgutwin.deckedout.ecs.components.physics.PositionComponent;
import ca.cgutwin.deckedout.ecs.components.physics.VelocityComponent;
import ca.cgutwin.deckedout.factories.Box2DBodyFactory;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Player extends Entity {

  private final TextureComponent textureComponent;

  public Player(Box2DBodyFactory bodyFactory, String texturePath, float x, float y) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(x, y);
    //    bodyDef.linearDamping = 0f;

    Body body = bodyFactory.createBody(bodyDef);

    textureComponent         = new TextureComponent();
    textureComponent.texture = new Texture(texturePath);

    PositionComponent positionComponent = new PositionComponent(x, y);
    VelocityComponent velocityComponent = new VelocityComponent(x, y, 2);
    Box2DComponent box2DComponent = new Box2DComponent(body);
    InputComponent inputComponent = new InputComponent();

    this.add(box2DComponent);
    this.add(textureComponent);
    this.add(positionComponent);
    this.add(velocityComponent);
    this.add(inputComponent);
  }

  public void update(float deltaTime) {
    // Update logic specific to the player
    // E.g., updating position based on velocity
  }

  public void dispose() {
    textureComponent.texture.dispose();
    // Dispose other resources if needed
  }
}
