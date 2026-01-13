package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import java.util.List;
import util.Vector;

public class VelocitySystem extends System {

  private final double FRICTION = 0.7;

  public VelocitySystem() {
    super(List.of(VelocityComponent.class, PositionComponent.class));
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              PositionComponent pc = entity.fetch(PositionComponent.class).get();
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

              pc.setPosition(
                  pc.getX() + vc.getVelocity().getX(), pc.getY() + vc.getVelocity().getY());
              vc.setVelocity(vc.getVelocity().scale(FRICTION));

              if ((vc.getVelocity().getX() > 0 && vc.getVelocity().getX() < 0.1)
                  || (vc.getVelocity().getX() < 0 && vc.getVelocity().getX() > 0.1)) {
                vc.setVelocity(Vector.of(0, vc.getVelocity().getY()));
              }
              if ((vc.getVelocity().getY() > 0 && vc.getVelocity().getY() < 0.1)
                  || (vc.getVelocity().getY() < 0 && vc.getVelocity().getY() > 0.1)) {
                vc.setVelocity(Vector.of(vc.getVelocity().getX(), 0));
              }
            });
  }
}
