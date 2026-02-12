package game.level.levellogic;

import ecs.components.PositionComponent;
import ecs.core.ECS;
import game.EntityFactory;
import game.level.LevelLoader;
import game.level.tiles.StarterTile;

public class LevelOne implements ILevel {

  @Override
  public void tick() {}

  @Override
  public void onLevelLeave() {

  }

  @Override
  public void onLevelLoad() {
    LevelLoader.getCurrentLevel().getLayout().forEach((point, tile) -> {
      if(tile instanceof StarterTile) {
        ECS.getHero().fetch(PositionComponent.class).get().setPosition(point.getX(),point.getY());
      }
    });
    ECS.addEntity(EntityFactory.createDummy(5, 5));
  }
}
