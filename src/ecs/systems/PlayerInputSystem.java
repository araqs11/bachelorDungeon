package ecs.systems;

import ecs.components.PlayerComponent;
import ecs.components.SkillComponent;
import ecs.components.VelocityComponent;
import game.level.LevelLoader;
import game.screens.GameLoop;
import util.InputHandler;
import util.Vector;

public class PlayerInputSystem extends System {

  private final double MOVEMENT_VELOCITY = 0.5;

  public PlayerInputSystem() {
    super(PlayerComponent.class);
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .forEach(
            entity -> {
              VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

              if (InputHandler.INPUT.get("FORWARD").isPressed()) {
                vc.addVelocity(Vector.of(0, -MOVEMENT_VELOCITY));
              }
              if (InputHandler.INPUT.get("BACKWARD").isPressed()) {
                vc.addVelocity(Vector.of(0, MOVEMENT_VELOCITY));
              }
              if (InputHandler.INPUT.get("LEFT").isPressed()) {
                vc.addVelocity(Vector.of(-MOVEMENT_VELOCITY, 0));
              }
              if (InputHandler.INPUT.get("RIGHT").isPressed()) {
                vc.addVelocity(Vector.of(MOVEMENT_VELOCITY, 0));
              }

              if(InputHandler.INPUT.get("USE_SKILL").isPressed()) {
                  entity.fetch(SkillComponent.class).ifPresent(skillComponent -> {
                      skillComponent.getCurrentSkill().execute(entity);
                  });
              }

              if (InputHandler.INPUT.get("ZOOM_OUT").isPressed()) {
                GameLoop.ZOOM--;
              }
              if (InputHandler.INPUT.get("ZOOM_IN").isPressed()) {
                GameLoop.ZOOM++;
              }
              if (InputHandler.INPUT.get("NEXT_LEVEL").isPressed()) {
                LevelLoader.loadNextLevel();
              }
            });
  }
}
