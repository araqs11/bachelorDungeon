package game.level;

import de.gurkenlabs.litiengine.resources.Resources;

public class FloorTile extends Tile {

    public FloorTile() {
        this.texture = Resources.images().get("enemy.png");
    }
}
