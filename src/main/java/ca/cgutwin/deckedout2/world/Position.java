package ca.cgutwin.deckedout2.world;

public class Position
{
  public static final float PIXELS_TO_METERS = 1/32f;

  public float x, y;

  public Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public static Position toLibGDXPosition(float x, float y) {
    return new Position(x*PIXELS_TO_METERS, y*PIXELS_TO_METERS);
  }
}
