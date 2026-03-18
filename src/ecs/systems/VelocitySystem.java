package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import ecs.core.ECS;
import ecs.core.System;
import java.util.List;
import util.Vector;

public class VelocitySystem implements System {

  private final double FRICTION = 0.2;
  private final double STOP_LIMIT = 0.1;

  @Override
  public void execute() {
    ECS.getRelevantEntities(List.of(VelocityComponent.class, PositionComponent.class))
        .forEach(
            entity -> {
              PositionComponent pc = entity.fetch(PositionComponent.class).get();
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

              vc.setVelocity(vc.getVelocity().scale(FRICTION));

              if ((vc.getVelocity().getX() > 0 && vc.getVelocity().getX() < STOP_LIMIT)
                  || (vc.getVelocity().getX() < 0 && vc.getVelocity().getX() > STOP_LIMIT)) {
                vc.setVelocity(Vector.of(0, vc.getVelocity().getY()));
              }
              if ((vc.getVelocity().getY() > 0 && vc.getVelocity().getY() < STOP_LIMIT)
                  || (vc.getVelocity().getY() < 0 && vc.getVelocity().getY() > STOP_LIMIT)) {
                vc.setVelocity(Vector.of(vc.getVelocity().getX(), 0));
              }
            });
  }
}
