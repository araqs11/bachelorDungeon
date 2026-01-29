package game.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.util.geom.Vector2D;
import ecs.Entity;
import ecs.components.*;
import game.PositionCamera;
import util.Vector;
import util.statemaschine.State;
import util.statemaschine.StateMachine;

public class EntityFactory {

  public static Entity createPlayer() {
    Entity player = new Entity("Player");

    StateMachine sm = new StateMachine();

    sm.addState(new State("idle1", "entities/player.png"));
    sm.addState(new State("idle2", "entities/enemy.png"));

    sm.addTransition("idle1", "idle2");
    sm.addTransition("idle2", "idle1");

    player.addComponent(new DrawComponent(sm, 10));
    player.addComponent(new PositionComponent(1, 1));
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
    Game.world().setCamera(new PositionCamera(player));
    return player;
  }

  public static Entity createDummy(int x, int y) {
    Entity dummy = new Entity("dummy");
    dummy.addComponent(new PositionComponent(x, y));
    dummy.addComponent(new CollisionComponent(Vector.of(1, 1)));
    StateMachine sm = new StateMachine();
    sm.addState(new State("idle", "entities/enemy.png"));
    dummy.addComponent(new DrawComponent(sm));
    return dummy;
  }
}
