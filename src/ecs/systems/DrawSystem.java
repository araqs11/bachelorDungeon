package ecs.systems;

import de.gurkenlabs.litiengine.Game;
import ecs.components.DrawComponent;
import ecs.components.PositionComponent;
import game.screens.GameLoop;
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
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            });
  }
}
