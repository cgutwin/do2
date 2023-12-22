package ca.cgutwin.deckedout;

public class MovementComponent implements Component {
  public float speed;
  public float velocityX = 0, velocityY = 0;

  public MovementComponent(float speed) {
    this.speed = speed;
  }
}

