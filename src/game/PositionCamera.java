package game;

import de.gurkenlabs.litiengine.graphics.Camera;
import ecs.Entity;
import ecs.components.PositionComponent;

import java.awt.geom.Point2D;

public class PositionCamera extends Camera {
    Entity entity;

    public PositionCamera(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void updateFocus() {
        final Point2D cameraLocation = this.getLockedCameraLocation();

        this.setFocus(cameraLocation);
        super.updateFocus();
    }

    protected Point2D getLockedCameraLocation() {
        PositionComponent pc = this.entity.fetch(PositionComponent.class).get();
        return new Point2D.Double(pc.getX(),pc.getY());
    }
}

