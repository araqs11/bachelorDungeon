package ecs.systems;

import de.gurkenlabs.litiengine.util.geom.Vector2D;
import ecs.components.PlayerComponent;
import ecs.components.VelocityComponent;
import util.InputHandler;
import util.Vector;

public class PlayerInputSystem extends System {

    private final double MOVEMENT_VELOCITY = 1.2;

    public PlayerInputSystem() {
        super(PlayerComponent.class);
    }

    @Override
    public void execute() {
        getRelevantEntities().forEach(entity -> {
            VelocityComponent vc = entity.fetch(VelocityComponent.class).get();

            if(InputHandler.INPUT.get("FORWARD").isPressed()) {
                vc.addVelocity(Vector.of(0,-MOVEMENT_VELOCITY));
            }
            if(InputHandler.INPUT.get("BACKWARD").isPressed()) {
                vc.addVelocity(Vector.of(0,MOVEMENT_VELOCITY));
            }
            if(InputHandler.INPUT.get("LEFT").isPressed()) {
                vc.addVelocity(Vector.of(-MOVEMENT_VELOCITY,0));
            }
            if(InputHandler.INPUT.get("RIGHT").isPressed()) {
                vc.addVelocity(Vector.of(MOVEMENT_VELOCITY,0));
            }

        });
    }
}
