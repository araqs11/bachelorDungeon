package ecs.components;

import de.gurkenlabs.litiengine.util.geom.Vector2D;
import ecs.Entity;

import java.util.function.BiConsumer;

public class CollisionComponent extends Component {

  private Vector2D vector;
    public BiConsumer<Entity, Entity> collideEnter = (a,b) -> {};
    public BiConsumer<Entity, Entity> collideLeave= (a,b) -> {};
    public BiConsumer<Entity, Entity> collideHold= (a,b) -> {};

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
