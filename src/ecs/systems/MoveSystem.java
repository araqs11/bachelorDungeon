package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import game.level.LevelLoader;
import game.level.Tile;
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

              double xNew = pc.getX() + vc.getVelocity().getX();
              double yNew = pc.getY() + vc.getVelocity().getY();

              HashMap<Point, Tile> map = LevelLoader.getCurrentLevel().getLayout();

              if (map.get(new Point((int) xNew, (int) yNew)).isWallLike()
                  || map.get(new Point((int) xNew + 1, (int) yNew + 1)).isWallLike()) {
                  // unterscheidung von richtungen und dann nur die entsprechenden Richtungen blocken/auf ((int)ynew)-1/((int)xnew)-1 setzen
                  pc.setPosition(pc.getX(),pc.getY());
              } else {
                pc.setPosition(
                    pc.getX() + vc.getVelocity().getX(), pc.getY() + vc.getVelocity().getY());
              }
            });
  }
}
