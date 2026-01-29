package game.level;

import de.gurkenlabs.litiengine.resources.Resources;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

/** Handles the file operation and storage for the LevelSystem. */
public class LevelLoader {

  private static final int TILESIZE = 16;
  private static ArrayList<Level> allLevels = new ArrayList<>();
  private static int currentLevel = 0;

  public static void loadFile(String filename) {
    String[] levelAsString = Resources.read(filename).split("\n");
    try {
      Level level = new Level(levelAsString[0].strip());
      for (int i = 1; i < levelAsString.length; i++) {
        String line = levelAsString[i].strip();
        for (int j = 0; j < line.length(); j++) {
          level.addTile((i - 1), j, getTileFromChar(line.charAt(j)));
        }
      }
      allLevels.add(level);
    } catch (RuntimeException e) {
      {
        Logger.getLogger("LevelLogger").severe(e.getMessage() + " in level file: " + filename);
      }
    } catch (Exception e) {
      Logger.getLogger("LevelLogger").severe("Error at loading level file: " + filename);
    }
  }

  public static Optional<Level> getLevel(String name) {
    return allLevels.stream().filter(level -> level.getName().equals(name)).findFirst();
  }

  public static Level getCurrentLevel() {
    return allLevels.get(currentLevel);
  }

  public static void loadNextLevel() {
    currentLevel++;
  }

  private static Tile getTileFromChar(char c) {
    return switch (c) {
      case 'F' -> new FloorTile();
      case 'W' -> new WallTile();
      default -> throw new RuntimeException("Invalid character: " + c);
    };
  }
}
