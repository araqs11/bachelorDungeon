package game.level.levellogic;

import ecs.core.ECS;
import game.EntityFactory;

public class DefaultLevel implements ILevel {

  @Override
  public void tick() {}

  @Override
  public void onLevelLeave() {
    System.out.println("onLevelLeave");
  }

  @Override
  public void onLevelLoad() {
    System.out.println("onLevelLoad");
    ECS.addEntity(EntityFactory.createDummy(3, 3));
  }
}
