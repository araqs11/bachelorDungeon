package ecs.systems;

import de.gurkenlabs.litiengine.Game;
import ecs.components.DrawComponent;
import ecs.components.HealthComponent;
import ecs.components.PositionComponent;
import ecs.core.System;
import game.GameLoop;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class DrawSystem extends System {

  public DrawSystem() {
    super(List.of(DrawComponent.class, PositionComponent.class));
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              DrawComponent dc = entity.fetch(DrawComponent.class).get();
              PositionComponent pc = entity.fetch(PositionComponent.class).get();
              try {
                Game.graphics()
                    .renderImage(
                        GameLoop.graphics,
                        dc.getScaledImage(),
                        pc.getX() * GameLoop.RENDERSCALE,
                        pc.getY() * GameLoop.RENDERSCALE);
                dc.nextImage();
                entity
                    .fetch(HealthComponent.class)
                    .ifPresent(
                        hc -> {
                          if (!hc.isRenderHealth()) {
                            return;
                          }
                          renderHealth(hc, pc);
                        });
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            });
  }

  private void renderHealth(HealthComponent hc, PositionComponent pc) {
    int healthBarHeight = 2;
    double maxSegments = 16.0;
    double segmentWidth = maxSegments / hc.getMaxHealth();

    for (int segment = 0; segment < hc.getMaxHealth(); segment++) {
      if (segment < hc.getCurrentHealth()) {
        renderSegment(
            pc.getX(), pc.getY(), segmentWidth, healthBarHeight, Color.RED, segment * segmentWidth);
      }
    }

    renderOutline(pc.getX(), pc.getY(), healthBarHeight, Color.BLACK);
  }

  private void renderSegment(
      double x, double y, double width, double height, Color color, double offset) {
    Rectangle2D rect =
        new Rectangle2D.Double(
            x * GameLoop.RENDERSCALE + offset, y * GameLoop.RENDERSCALE + 16, width, height);
    GameLoop.graphics.setColor(color);
    Game.graphics().renderShape(GameLoop.graphics, rect);
  }

  private void renderOutline(double x, double y, double height, Color color) {
    Rectangle2D rect =
        new Rectangle2D.Double(x * GameLoop.RENDERSCALE, y * GameLoop.RENDERSCALE + 16, 16, height);
    GameLoop.graphics.setColor(color);
    Game.graphics().renderOutline(GameLoop.graphics, rect);
  }
}
