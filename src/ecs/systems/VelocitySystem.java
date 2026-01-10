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

    }
}
