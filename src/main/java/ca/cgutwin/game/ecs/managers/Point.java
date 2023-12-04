package ca.cgutwin.game.ecs.managers;

public record Point(int x, int y) {
  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (!(o instanceof final Point point)) { return false; }
    return x == point.x && y == point.y;
  }

}
