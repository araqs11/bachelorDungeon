package ecs.systems;

import de.gurkenlabs.litiengine.util.geom.Vector2D;
import ecs.components.PositionComponent;
import ecs.components.ProjectileComponent;
import ecs.components.VelocityComponent;
import ecs.core.ECS;
import ecs.core.Entity;
import ecs.core.System;

public class ProjectileSystem extends System {

  public ProjectileSystem() {
    super(ProjectileComponent.class);
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              ProjectileComponent projectileComponent =
                  entity.fetch(ProjectileComponent.class).get();
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

              if (reachedEnd(entity)) {
                ECS.removeEntity(entity);
              } else {
                Vector2D projectileVector =
                    projectileComponent
                        .getEndPoint()
                        .sub(projectileComponent.getStartPoint())
                        .unitVector()
                        .scale(projectileComponent.getSpeed());
                vc.setVelocity(projectileVector);
              }
            });
  }

  public boolean reachedEnd(Entity entity) {
    ProjectileComponent projectileComponent = entity.fetch(ProjectileComponent.class).get();
    PositionComponent positionComponent = entity.fetch(PositionComponent.class).get();

    return positionComponent.getPosition().distance(projectileComponent.getStartPoint())
        > projectileComponent.getRange();
  }
}
