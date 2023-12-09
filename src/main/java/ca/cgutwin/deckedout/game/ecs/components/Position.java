package ca.cgutwin.deckedout.game.ecs.components;

public class Position {
  private float x;
  private float y;

  public Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  // Getters
  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  // Setters
  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  // Method to update position
  public void update(float deltaX, float deltaY) {
    this.x += deltaX;
    this.y += deltaY;
  }

  // Method to set position
  public void setPosition(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public float distanceTo(Position other) {
    return (float) Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
  }

  @Override
  public String toString() {
    return "Position{" + "x=" + x + ", y=" + y + '}';
  }
}
