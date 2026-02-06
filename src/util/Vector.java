package util;

import de.gurkenlabs.litiengine.util.geom.Vector2D;

/** Wrapper class for Vector2D for easier handling. */
public class Vector extends Vector2D {

  public Vector(double x, double y) {
    super(x,y);
  }

  public static Vector of(double x, double y) {
    return new Vector(x, y);
  }

  public static Vector2D ZERO = Vector.of(0,0);

  public double distance(Vector other) {
    return Math.sqrt(Math.pow(dX-other.dX,2)+Math.pow(dY-other.dY,2));
  }

  public Vector add(Vector2D v1) {
    return new Vector(dX+v1.getX(),dY+v1.getY());
  }
}
