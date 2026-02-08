package ecs.components;

import ecs.core.Component;
import util.Vector;

public class PositionComponent extends Component {

  private double x;
  private double y;

  public PositionComponent(double x, double y) {
    super();
    this.x = x;
    this.y = y;
  }

  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public Vector getPosition() {
    return Vector.of(x, y);
  }
}
