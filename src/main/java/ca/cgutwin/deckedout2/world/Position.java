package ca.cgutwin.deckedout2.world;

import com.badlogic.gdx.math.Vector2;

public class Position
{
  public static final float PIXELS_TO_METERS = 1/32f;

  public float x, y;

  public Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public static Position toLibGDXPosition(Vector2 vector2) {
    return toLibGDXPosition(vector2.x, vector2.y);
  }

  public static Position toLibGDXPosition(float x, float y) {
    return new Position(x*PIXELS_TO_METERS, y*PIXELS_TO_METERS);
  }

  public static Position fromVector2(Vector2 vector) {
    return new Position(vector.x, vector.y);
  }

  public Vector2 toVector2() {
    return new Vector2(x, y);
  }

  public void setFromVector2(Vector2 vector) {
    this.x = vector.x;
    this.y = vector.y;
  }
}
