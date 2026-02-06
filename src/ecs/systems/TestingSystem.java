package ecs.systems;

import de.gurkenlabs.litiengine.Game;
import ecs.components.PlayerComponent;
import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import game.screens.GameLoop;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import util.input.InputHandler;

public class TestingSystem extends System {

  public TestingSystem() {
    super(List.of(PlayerComponent.class));
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              if (!InputHandler.input.get("TESTING").isPressed()) {
                return;
              }
              PositionComponent pc = entity.fetch(PositionComponent.class).get();
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

              renderPoint(pc.getX() + 0.5, pc.getY() + 0.5);
            });
  }

  private void renderPoint(double x, double y) {
    Rectangle2D rect1 =
        new Rectangle2D.Double(x * GameLoop.RENDERSCALE, y * GameLoop.RENDERSCALE, 1, 1);
    GameLoop.graphics.setColor(Color.RED);
    Game.graphics().renderShape(GameLoop.graphics, rect1);
  }

  private void renderPoint(Point p, Color c) {
    Rectangle2D rect1 =
        new Rectangle2D.Double(
            p.getX() * GameLoop.RENDERSCALE, p.getY() * GameLoop.RENDERSCALE, 2, 2);
    GameLoop.graphics.setColor(c);
    Game.graphics().renderShape(GameLoop.graphics, rect1);
  }
}
