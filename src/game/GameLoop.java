package game;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import ecs.core.ECS;

import java.awt.*;

public class GameLoop extends GameScreen {

  public static Graphics2D graphics;
  public static int ZOOM = 4;
  public static final int RENDERSCALE = 16;

  public GameLoop() {}

  /**
   * Main game loop.
   *
   * @param g The current graphics object onto which this instance will render its visual contents.
   */
  @Override
  public void render(Graphics2D g) {
    super.render(g);
    graphics = g;
    ECS.scheduler.tick();
  }
}
