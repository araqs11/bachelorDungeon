package game.level;

import de.gurkenlabs.litiengine.resources.Resources;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Handles the file operation and storage for the LevelSystem.
 */
public class LevelLoader {

    private static int TILESIZE = 16;
    private static HashMap<String, Level> allLevels = new HashMap<>();


    public static void loadFile(String filename) {
       String[] levelAsString =  Resources.read(filename).split("\n");
       try {
           Level level = new Level();
           for (int i = 1; i < levelAsString.length; i++) {
               String line = levelAsString[i];
               for (int j = 0; j < line.length(); j++) {
                   level.addTile(i*TILESIZE, j*TILESIZE,getTileFromChar(line.charAt(j)));
               }
           }
           allLevels.put(levelAsString[0], level);
       } catch (Exception e) {
           Logger.getLogger("LevelLogger").severe("Error at loading level file: " + filename);
       }
    }

    public static Level getLevel(String name) {
        return allLevels.get(name);
    }

    private static Tile getTileFromChar(char c) {
        switch (c) {
            case 'F': return new FloorTile();
            case 'W': return new WallTile();
            default: return new FloorTile();
        }
    }

}
