package ecs.systems;

import de.gurkenlabs.litiengine.Game;
import game.level.LevelLoader;
import game.screens.GameLoop;

/** Handles rendering the level from the LevelLoader. */
public class LevelSystem extends System {

  @Override
  public void execute() {
    LevelLoader.getCurrentLevel()
        .getLayout()
        .forEach(
            (point, tile) -> {
              Game.graphics()
                  .renderImage(
                      GameLoop.graphics,
                      tile.getTexture()
                          .getScaledInstance(
                              GameLoop.RENDERSCALE * GameLoop.ZOOM,
                              GameLoop.RENDERSCALE * GameLoop.ZOOM,
                              1),
                      point.getX() * GameLoop.RENDERSCALE,
                      point.getY() * GameLoop.RENDERSCALE);
            });
    LevelLoader.getCurrentLevel().getLevel().tick();
  }
}
