package ecs.systems;

import de.gurkenlabs.litiengine.Game;
import ecs.core.System;
import game.GameLoop;
import game.level.LevelLoader;

/** Handles rendering the level from the LevelLoader. */
public class LevelSystem implements System {

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
