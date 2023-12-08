package ca.cgutwin.deckedout.game.ecs.components;

public class MovementComponent implements Component {
  public float speed;
  public float velocityX = 0, velocityY = 0;
  public float sprintSpeed;

  public MovementComponent(float speed) {
    this.speed = speed;
    this.sprintSpeed = this.speed * 1.5F;
  }
}
