package game.level;

import java.awt.*;
import java.util.HashMap;

public class Level {

  private HashMap<Point, Tile> layout;

  public Level() {
    this.layout = new HashMap<>();
  }

  public void addTile(int x, int y, Tile tile) {
    layout.put(new Point(x, y), tile);
  }

  public HashMap<Point, Tile> getLayout() {
    return layout;
  }
}
