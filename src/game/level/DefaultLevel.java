package game.level;

import ecs.ECS;
import game.entities.EntityFactory;

public class DefaultLevel implements ILevel{

    @Override
    public void tick() {

    }

    @Override
    public void onLevelLeave() {
        System.out.println("onLevelLeave");

    }

    @Override
    public void onLevelLoad() {
        System.out.println("onLevelLoad");
        ECS.addEntity(EntityFactory.createDummy(3,3));
    }
}
