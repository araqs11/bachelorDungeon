package ecs.components;

import de.gurkenlabs.litiengine.util.geom.Vector2D;

public class VelocityComponent extends Component {

    private Vector2D velocity;

    public VelocityComponent(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
