package ecs.systems;

import ecs.components.PlayerComponent;
import ecs.components.SkillComponent;
import ecs.components.VelocityComponent;
import ecs.core.ECS;
import ecs.core.System;
import game.GameLoop;
import game.level.LevelLoader;
import util.Vector;
import util.input.InputHandler;
import util.input.InputName;

import java.util.List;

public class PlayerInputSystem extends System {

  private final double MOVEMENT_VELOCITY = 0.5;


  @Override
  public void execute() {
      ECS.getRelevantEntities(List.of(PlayerComponent.class))
        .forEach(
            entity -> {
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

              if (InputHandler.isPressed(InputName.FORWARD)) {
                vc.addVelocity(Vector.of(0, -MOVEMENT_VELOCITY));
              }
              if (InputHandler.isPressed(InputName.BACKWARD)) {
                vc.addVelocity(Vector.of(0, MOVEMENT_VELOCITY));
              }
              if (InputHandler.isPressed(InputName.LEFT)) {
                vc.addVelocity(Vector.of(-MOVEMENT_VELOCITY, 0));
              }
              if (InputHandler.isPressed(InputName.RIGHT)) {
                vc.addVelocity(Vector.of(MOVEMENT_VELOCITY, 0));
              }

              if (InputHandler.isPressed(InputName.USE_SKILL)) {
                entity
                    .fetch(SkillComponent.class)
                    .ifPresent(
                        skillComponent -> {
                          skillComponent.getCurrentSkill().execute(entity);
                        });
              }

              if (InputHandler.isPressed(InputName.ZOOM_OUT)) {
                GameLoop.ZOOM--;
              }
              if (InputHandler.isPressed(InputName.ZOOM_IN)) {
                GameLoop.ZOOM++;
              }
              if (InputHandler.isPressed(InputName.NEXT_LEVEL)) {
                LevelLoader.loadNextLevel();
              }
            });
  }
}
