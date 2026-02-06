package game.level;

import game.level.levellogic.ILevel;
import game.level.tiles.Tile;
import java.awt.*;
import java.util.HashMap;

public class LevelData {

  private HashMap<Point, Tile> layout;
  private String name;
  private ILevel level;

  public LevelData(String levelName, ILevel level) {
    this.name = levelName;
    this.layout = new HashMap<>();
    this.level = level;
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

  public ILevel getLevel() {
    return level;
  }
}
