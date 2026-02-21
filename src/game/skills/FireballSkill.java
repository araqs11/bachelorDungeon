package game.skills;

import ecs.components.*;
import ecs.core.ECS;
import ecs.core.Entity;
import game.AudioPlayer;
import util.Util;
import util.Vector;
import util.statemaschine.State;
import util.statemaschine.StateMachine;

public class FireballSkill extends Skill {

  @Override
  public void execute(Entity caster) {
    PositionComponent pc = caster.fetch(PositionComponent.class).get();

    Entity fireball = new Entity("fireBall");
    StateMachine sm = new StateMachine();
    sm.addState(new State("idle", "entities/fire.png"));
    fireball.addComponent(new DrawComponent(sm));
    fireball.addComponent(new PositionComponent(pc.getX(), pc.getY()));
    fireball.addComponent(new VelocityComponent(Vector.ZERO));

    Vector endPoint = pc.getPosition().add(Util.getRelativeCursorPosition());
    fireball.addComponent(new ProjectileComponent(3f, 1f, pc.getPosition(), endPoint));

    CollisionComponent cc = new CollisionComponent(Vector.ZERO);

    cc.collideEnter =
        (me, other) -> {
          other
              .fetch(HealthComponent.class)
              .ifPresent(
                  hc -> {
                    if (other.equals(caster)) {
                      return;
                    }
                    hc.reduce(2);
                    System.out.println(me.getName() + " hit " + other.getName());
                  });
        };
    fireball.addComponent(cc);
    ECS.addEntity(fireball);
    AudioPlayer.playSound("sounds/fireball.wav");
  }
}
