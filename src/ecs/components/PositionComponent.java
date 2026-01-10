package ecs.components;

public class PositionComponent extends Component {

  private int x;
  private int y;

  public PositionComponent(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
