package game;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.util.geom.Vector2D;
import ecs.components.*;
import ecs.core.Entity;
import game.skills.FireballSkill;
import util.Vector;
import util.statemaschine.State;
import util.statemaschine.StateMachine;

import java.awt.*;

public class EntityFactory {

  public static Entity createPlayer(double x, double  y) {
    Entity player = new Entity("Player");

    StateMachine sm = new StateMachine();

    sm.addState(new State("idle1", "entities/player.png"));
    //    sm.addState(new State("idle2", "entities/enemy.png"));

    //    sm.addTransition("idle1", "idle2");
    //    sm.addTransition("idle2", "idle1");

    player.addComponent(new DrawComponent(sm, 10));
    player.addComponent(new PositionComponent(x,y));
    player.addComponent(new VelocityComponent(new Vector2D(0, 0)));
    player.addComponent(new PlayerComponent());
    CollisionComponent cc = new CollisionComponent(Vector.of(1, 1));
    cc.collideEnter =
        (a, b) -> {
          System.out.println(a.getName() + " collided with " + b.getName());
        };

    cc.collideHold =
        (a, b) -> {
          // System.out.println("collideHold");
        };

    cc.collideLeave =
        (a, b) -> {
          System.out.println(a.getName() + " left the collision with " + b.getName());
        };
    player.addComponent(cc);
    SkillComponent sc = new SkillComponent();
    sc.addSkill(new FireballSkill());
    player.addComponent(sc);
    HealthComponent hc = new HealthComponent(32);
    hc.setCurrentHealth(16);
    hc.setRenderHealth(true);
    player.addComponent(hc);
    Game.world().setCamera(new PositionCamera(player));
    return player;
  }

  public static Entity createPlayer(Point p) {
    return createPlayer(p.getX(),p.getY());
  }

  public static Entity createDummy(int x, int y) {
    Entity dummy = new Entity("dummy");
    StateMachine sm = new StateMachine();
    sm.addState(new State("idle", "entities/enemy.png"));
    dummy.addComponent(new DrawComponent(sm));
    dummy.addComponent(new PositionComponent(x, y));
    dummy.addComponent(new VelocityComponent(Vector.ZERO));
    CollisionComponent cc = new CollisionComponent(Vector.of(1, 1));
    cc.collideEnter =
            (me, other) -> {
              other.fetch(HealthComponent.class).ifPresent(hc->{
                hc.reduce(1);
              });
            };
    dummy.addComponent(cc);
    HealthComponent hc = new HealthComponent(5);
    hc.setRenderHealth(true);
    dummy.addComponent(hc);
    dummy.addComponent(new RandomMoveBehaviourComponent());
    return dummy;
  }
}
