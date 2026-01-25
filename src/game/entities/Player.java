package game.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.util.geom.Vector2D;
import ecs.Entity;
import ecs.components.*;
import game.PositionCamera;
import util.Vector;

public class Player {

  public static Entity createPlayer() {
    Entity player = new Entity("Player");
    player.addComponent(new DrawComponent("entities/player.png"));
    player.addComponent(new PositionComponent(1, 1));
    player.addComponent(new VelocityComponent(new Vector2D(0, 0)));
    player.addComponent(new PlayerComponent());
    CollisionComponent cc = new CollisionComponent(Vector.of(1, 1));
    player.addComponent(cc);
    Game.world().setCamera(new PositionCamera(player));
    return player;
  }
}
