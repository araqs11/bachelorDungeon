package ecs.systems;

import ecs.components.HealthComponent;
import ecs.core.System;

public class HealthSystem extends System {

  public HealthSystem() {
    super(HealthComponent.class);
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              HealthComponent hc = entity.fetch(HealthComponent.class).get();
              if (hc.getCurrentHealth() <= 0) {
                hc.getOnDeath().accept(entity);
              }
            });
  }
}
