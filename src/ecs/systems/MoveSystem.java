package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import game.level.LevelLoader;
import java.awt.*;
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

              double x = pc.getX();
              double y = pc.getY();
              double xNew = x + vc.getVelocity().getX();
              double yNew = y + vc.getVelocity().getY();

              Point xPointTopLeft = new Point((int) xNew, (int) y);
              Point xPointBottomRight = new Point((int) xNew + 1, (int) y);
              Point yPointTopLeft = new Point((int) x, (int) yNew);
              Point yPointBottomRight = new Point((int) x, (int) yNew + 1);

              if (LevelLoader.getCurrentLevel().getLayout().get(xPointTopLeft).isWallLike()
                  || LevelLoader.getCurrentLevel()
                      .getLayout()
                      .get(xPointBottomRight)
                      .isWallLike()) {
                double dx = vc.getVelocity().getX();
                if (dx < 0) {
                  // nach links gehen da negative velocity
                  x = ((int) xNew) + 1.001;
                } else if (dx > 0) {
                  // nach rechts gehen da positive velocity
                  x = ((int) xNew) - 0.001;
                }
              } else {
                x = xNew;
              }

              if (LevelLoader.getCurrentLevel().getLayout().get(yPointTopLeft).isWallLike()
                  || LevelLoader.getCurrentLevel()
                      .getLayout()
                      .get(yPointBottomRight)
                      .isWallLike()) {
                double dy = vc.getVelocity().getY();
                if (dy < 0) {
                  // nach oben gehen da negative velocity
                  y = ((int) yNew) + 1.001;
                } else if (dy > 0) {
                  // nach unten gehen da positive velocity
                  y = ((int) yNew) - 0.001;
                }
              } else {
                y = yNew;
              }

              pc.setPosition(x, y);
            });
  }
}
