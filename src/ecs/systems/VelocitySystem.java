package ecs.systems;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import java.util.List;

public class VelocitySystem extends System {

  public VelocitySystem() {
    super(List.of(VelocityComponent.class, PositionComponent.class));
  }

  @Override
  public void execute() {
    getRelevantEntities().forEach(entity -> {
      PositionComponent pc = entity.fetch(PositionComponent.class).get();
      VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

      pc.setPosition(pc.getX()+vc.getVelocity().getX(),pc.getY()+vc.getVelocity().getY());
    });
  }
}
