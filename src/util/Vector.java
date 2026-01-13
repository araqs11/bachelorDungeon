package util;

import de.gurkenlabs.litiengine.util.geom.Vector2D;

/** Wrapper class for Vector2D for easier handling. */
public class Vector extends Vector2D {

  public static Vector2D of(double x, double y) {
    return new Vector2D(x, y);
  }
}
