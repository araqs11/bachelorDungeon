package ecs.components;

import ecs.core.Component;
import ecs.core.ECS;
import ecs.core.Entity;
import util.Vector;

import java.util.function.Consumer;

public class ProjectileComponent extends Component {

    private float range;
    private float speed;
    private Vector endPoint;
    private Vector startPoint;
    private Consumer<Entity> onWallHit;

    public ProjectileComponent(float range, float speed, Vector startPoint, Vector endPoint, Consumer<Entity> onWallHit) {
        this.range = range;
        this.speed = speed;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.onWallHit = onWallHit;
    }

    public ProjectileComponent(float range, float speed, Vector startPoint,Vector endPoint) {
        this(range,speed,startPoint, endPoint, ECS::removeEntity);
    }

    public float getRange() {
        return range;
    }

    public float getSpeed() {
        return speed;
    }

    public Consumer<Entity> getOnWallHit() {
        return onWallHit;
    }

    public Vector getEndPoint() {
        return endPoint;
    }

    public Vector getStartPoint() {
        return startPoint;
    }
}
