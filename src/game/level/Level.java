package game.level;

import game.level.tiles.Tile;
import java.awt.*;
import java.util.HashMap;

public class Level {

  private HashMap<Point, Tile> layout;
  private String name;

  public Level(String levelName) {
    this.name = levelName;
    this.layout = new HashMap<>();
  }

  public void addTile(int x, int y, Tile tile) {
    layout.put(new Point(x, y), tile);
  }

  public HashMap<Point, Tile> getLayout() {
    return layout;
  }

  public String getName() {
    return name;
  }
}
