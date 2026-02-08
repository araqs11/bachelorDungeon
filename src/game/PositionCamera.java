package game;

import de.gurkenlabs.litiengine.graphics.Camera;
import ecs.components.PositionComponent;
import ecs.core.Entity;
import java.awt.geom.Point2D;

public class PositionCamera extends Camera {
  Entity entity;

  public PositionCamera(Entity entity) {
    this.entity = entity;
    this.setClampToMap(true);
  }

  @Override
  public void updateFocus() {
    final Point2D cameraLocation = this.getLockedCameraLocation();
    this.setZoom(GameLoop.ZOOM, 0);
    this.setFocus(cameraLocation);
    super.updateFocus();
  }

  protected Point2D getLockedCameraLocation() {
    PositionComponent pc = this.entity.fetch(PositionComponent.class).get();
    return new Point2D.Double(
        (pc.getX() + 0.5) * GameLoop.RENDERSCALE, (pc.getY() + 0.5) * GameLoop.RENDERSCALE);
  }
}
