package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import game.level.LevelLoader;
import game.level.tiles.Tile;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

/** Checks if the new position of the entity is allowed. */
public class MoveSystem extends System {

  public MoveSystem() {
    super(List.of(VelocityComponent.class, PositionComponent.class));
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              PositionComponent pc = entity.fetch(PositionComponent.class).get();
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();
              float x = (float) pc.getX();
              float y = (float) pc.getY();
              double velX = vc.getVelocity().getX();
              double velY = vc.getVelocity().getY();

              // Move X
              x += velX;
              if (collidesWithWorld(x, y, 1, 1, LevelLoader.getCurrentLevel().getLayout())) {
                  if (velX < 0) {
                      x = (float) (Math.ceil(x) + 0.0001f);
                  } else if (velX > 0) {
                      x = (float) (Math.floor(x) - 0.0001f);
                  }
              }

              // Move Y
              y += velY;
              if (collidesWithWorld(x, y, 1, 1, LevelLoader.getCurrentLevel().getLayout())) {
                  if (velY < 0) {
                      y = (float) (Math.ceil(y) + 0.0001f);
                  } else if (velY > 0) {
                      y = (float) (Math.floor(y) - 0.0001f);
                  }
              }
              pc.setPosition(x, y);
            });
  }

  public static boolean collidesWithWorld(
      float x, float y, float width, float height, HashMap<Point, Tile> world) {
    // Bounding box in world (tile) coordinates
    float left = x;
    float right = x + width;
    float top = y;
    float bottom = y + height;

    // Convert bounds to tile indices
    int leftTile = (int) Math.floor(left);
    int rightTile = (int) Math.floor(right - 0.0001f);
    int topTile = (int) Math.floor(top);
    int bottomTile = (int) Math.floor(bottom - 0.0001f);

    // Check bounds (outside world = solid)
    //        if (leftTile < 0 || topTile < 0 ||
    //                rightTile >= world.length ||
    //                bottomTile >= world[0].length) {
    //            return true;
    //        }

    // Check all overlapped tiles
    for (int tx = leftTile; tx <= rightTile; tx++) {
      for (int ty = topTile; ty <= bottomTile; ty++) {
        if (world.get(new Point(tx, ty)).isWallLike()) {
          return true;
        }
      }
    }

    return false;
  }
}
