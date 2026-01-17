package game.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import ecs.ECS;
import ecs.Entity;
import ecs.systems.*;
import ecs.systems.System;
import java.awt.*;

public class GameLoop extends GameScreen {

  public static Graphics2D graphics;
  public final static int ZOOM = 3;

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
