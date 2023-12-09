package ca.cgutwin.deckedout.game.ecs.components;


public class PositionComponent implements Component {
  private final Position position;
  public float x, y;

  public PositionComponent(float x, float y) {
    this.x        = x;
    this.y        = y;
    this.position = new Position(x, y);
  }

  public PositionComponent(Position position) {
    this.position = position;
    this.x        = position.getX();
    this.y        = position.getY();
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(float x, float y) {
    this.position.setPosition(x, y);
  }
}
