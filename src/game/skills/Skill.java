package game.skills;

import ecs.core.Entity;

public abstract class Skill {

  public abstract void execute(Entity caster);
}
