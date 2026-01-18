package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import game.level.LevelLoader;
import game.level.Tile;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import util.InputHandler;

public class TestingSystem extends System {

  public TestingSystem() {
    super(List.of(VelocityComponent.class, PositionComponent.class));
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              if (!InputHandler.INPUT.get("TESTING").isPressed()) {
                return;
              }
              PositionComponent pc = entity.fetch(PositionComponent.class).get();
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

              int currentX = (int) pc.getX();
              int currentY = (int) pc.getY();

              HashMap<Point, Tile> map = LevelLoader.getCurrentLevel().getLayout();
              java.lang.System.out.println();
              java.lang.System.out.println(map.get(new Point(currentX, currentY)).isWallLike());
              java.lang.System.out.println(
                  map.get(new Point(currentX + 1, currentY + 1)).isWallLike());

              //                            pc.setPosition(
              //                                    pc.getX() + vc.getVelocity().getX(), pc.getY() +
              // vc.getVelocity().getY());
            });
  }
}
