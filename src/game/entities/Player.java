package game.entities;

import ecs.Entity;
import ecs.components.DrawComponent;

public class Player {

    public static Entity createPlayer() {
        Entity player = new Entity("Player");
        player.addComponent(new DrawComponent("player.png"));
        return player;
    }
}
