package ecs.components;

import ecs.core.Component;
import util.Vector;

public class RandomMoveBehaviourComponent extends Component {

    private int cooldown = 1000;
    private long timeHolder;
    private Vector lastMovement;

    public Vector randomMoveEverySecond() {
        if (timeHolder + cooldown <= System.currentTimeMillis()) {
            timeHolder = System.currentTimeMillis();
            lastMovement = Vector.of((Math.random() * 2)-1 , (Math.random() * 2) - 1);
        }
        return lastMovement;
    }
}
