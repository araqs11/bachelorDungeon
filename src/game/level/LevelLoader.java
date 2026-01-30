package game.level;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import ecs.ECS;
import game.level.tiles.EmptyTile;
import game.level.tiles.FloorTile;
import game.level.tiles.Tile;
import game.level.tiles.WallTile;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

/** Handles the file operation and storage for the LevelSystem. */
public class LevelLoader {

  private static ArrayList<LevelData> allLevels = new ArrayList<>();
  private static int currentLevel = -1;

  public static void loadLevel(String filename, ILevel level) {
    String[] levelAsString = Resources.read(filename).split("\n");
    try {
      LevelData stringLevel = new LevelData(levelAsString[0].strip(), level);
      for (int i = 1; i < levelAsString.length; i++) {
        String line = levelAsString[i].strip();
        for (int j = 0; j < line.length(); j++) {
          stringLevel.addTile((i - 1), j, getTileFromChar(line.charAt(j)));
        }
      }
      allLevels.add(stringLevel);
    } catch (RuntimeException e) {
      Logger.getLogger("LevelLogger").severe(e.getMessage() + " in level file: " + filename);
      Game.exit();
    } catch (Exception e) {
      Logger.getLogger("LevelLogger").severe("Error at loading level file: " + filename);
      Game.exit();
    }
  }

  public static Optional<LevelData> getLevel(String name) {
    return allLevels.stream().filter(level -> level.getName().equals(name)).findFirst();
  }

  public static LevelData getCurrentLevel() {
    return allLevels.get(currentLevel);
  }

  public static void loadNextLevel() {
    if (currentLevel >= 0) {
      getCurrentLevel().getLevel().onLevelLeave();
    }
    currentLevel++;
    ECS.removeAllEntitiesButHero();
    getCurrentLevel().getLevel().onLevelLoad();
  }

  private static Tile getTileFromChar(char c) {
    return switch (c) {
      case 'F' -> new FloorTile();
      case 'W' -> new WallTile();
      case ' ' -> new EmptyTile();
      default -> throw new RuntimeException("Invalid character: " + c);
    };
  }
}
