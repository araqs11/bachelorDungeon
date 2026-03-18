package ecs.systems;

import ecs.components.HealthComponent;
import ecs.core.ECS;
import ecs.core.System;

import java.util.List;

public class HealthSystem extends System {



  @Override
  public void execute() {
      ECS.getRelevantEntities(List.of(HealthComponent.class))
        .forEach(
            entity -> {
              HealthComponent hc = entity.fetch(HealthComponent.class).get();
              if (hc.getCurrentHealth() <= 0) {
                hc.getOnDeath().accept(entity);
              }
            });
  }
}
