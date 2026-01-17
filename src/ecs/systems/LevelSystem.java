package ecs.systems;

import de.gurkenlabs.litiengine.Game;
import game.level.LevelLoader;
import game.screens.GameLoop;

/**
 * Handles rendering the level from the LevelLoader.
 */
public class LevelSystem extends System{

    @Override
    public void execute() {
        LevelLoader.getLevel("default").getLayout().forEach((point,tile) -> {
            Game.graphics().renderImage(GameLoop.graphics,tile.getTexture().getScaledInstance(tile.getTexture().getWidth()*GameLoop.ZOOM,tile.getTexture().getHeight()*GameLoop.ZOOM,1),point);
        });
    }
}
