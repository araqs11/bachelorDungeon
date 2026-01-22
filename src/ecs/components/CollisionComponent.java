package ecs.components;

import de.gurkenlabs.litiengine.util.geom.Vector2D;

public class CollisionComponent extends Component {

  private Vector2D vector;

  public CollisionComponent(Vector2D vector) {
    this.vector = vector;
  }

  public double getWidth() {
    return vector.getX();
  }

  public double getHeight() {
    return vector.getY();
  }
}
