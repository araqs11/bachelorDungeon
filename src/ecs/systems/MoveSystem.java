package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.ProjectileComponent;
import ecs.components.VelocityComponent;
import ecs.core.System;
import game.level.LevelLoader;
import game.level.tiles.Tile;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

/** Checks if the new position of the entity is allowed. */
public class MoveSystem extends System {

  private static final float COLLIDER_WIDTH = 0.9f;
  private static final float COLLIDER_HEIGHT = 0.9f;
  private static final float EPSILON = 0.0001f;

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
              float velX = (float) vc.getVelocity().getX();
              float velY = (float) vc.getVelocity().getY();
              boolean hasHitwall = false;

              // Move X
              x += velX;

              float colliderX = x + (1.0f - COLLIDER_WIDTH) / 2.0f;
              float colliderY = y + (1.0f - COLLIDER_HEIGHT) / 2.0f;

              if (collidesWithWorld(
                  colliderX,
                  colliderY,
                  COLLIDER_WIDTH,
                  COLLIDER_HEIGHT,
                  LevelLoader.getCurrentLevel().getLayout())) {
                if (velX > 0) {
                  // hit wall on the RIGHT
                  float tileX = (float) Math.floor(colliderX + COLLIDER_WIDTH);
                  colliderX = tileX - COLLIDER_WIDTH - EPSILON;
                } else if (velX < 0) {
                  // hit wall on the LEFT
                  float tileX = (float) Math.ceil(colliderX);
                  colliderX = tileX + EPSILON;
                }
                x = colliderX - (1.0f - COLLIDER_WIDTH) / 2.0f;
                hasHitwall = true;
              }

              // Move Y
              y += velY;

              colliderX = x + (1.0f - COLLIDER_WIDTH) / 2.0f;
              colliderY = y + (1.0f - COLLIDER_HEIGHT) / 2.0f;

              if (collidesWithWorld(
                  colliderX,
                  colliderY,
                  COLLIDER_WIDTH,
                  COLLIDER_HEIGHT,
                  LevelLoader.getCurrentLevel().getLayout())) {

                if (velY > 0) {
                  // hit wall at the BOTTOM
                  float tileY = (float) Math.floor(colliderY + COLLIDER_HEIGHT);
                  colliderY = tileY - COLLIDER_HEIGHT - EPSILON;
                } else if (velY < 0) {
                  // hit wall at the TOP
                  float tileY = (float) Math.ceil(colliderY);
                  colliderY = tileY + EPSILON;
                }

                // convert collider position back to sprite position
                y = colliderY - (1.0f - COLLIDER_HEIGHT) / 2.0f;
                hasHitwall = true;
              }

              if (hasHitwall) {
                entity
                    .fetch(ProjectileComponent.class)
                    .ifPresent(
                        projectileComponent -> {
                          projectileComponent.getOnWallHit().accept(entity);
                        });
              }

              pc.setPosition(x, y);
            });
  }

  public static boolean collidesWithWorld(
      float x, float y, float width, float height, HashMap<Point, Tile> world) {
    float left = x;
    float right = x + width;
    float top = y;
    float bottom = y + height;

    int leftTile = (int) Math.floor(left);
    int rightTile = (int) Math.floor(right - EPSILON);
    int topTile = (int) Math.floor(top);
    int bottomTile = (int) Math.floor(bottom - EPSILON);

    for (int tx = leftTile; tx <= rightTile; tx++) {
      for (int ty = topTile; ty <= bottomTile; ty++) {
        Tile tile = world.get(new Point(tx, ty));
        if (tile != null && tile.isWallLike()) {
          return true;
        }
      }
    }
    return false;
  }
}
