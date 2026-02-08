package game.level.levellogic;

import ecs.components.PositionComponent;
import ecs.components.VelocityComponent;
import ecs.core.ECS;
import ecs.core.Entity;
import game.level.LevelLoader;
import game.level.tiles.StarterTile;
import util.Vector;

public class LevelTwo implements ILevel{
    @Override
    public void tick() {

    }

    @Override
    public void onLevelLeave() {

    }

    @Override
    public void onLevelLoad() {
        LevelLoader.getCurrentLevel().getLayout().forEach((point, tile) -> {
            if(tile instanceof StarterTile) {
                Entity hero = ECS.getHero();
                hero.fetch(VelocityComponent.class).get().setVelocity(Vector.ZERO);
                hero.fetch(PositionComponent.class).get().setPosition(point.getX(),point.getY());
            }
        });
    }
}
