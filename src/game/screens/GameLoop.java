package game.entities.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import ecs.Entity;
import ecs.systems.*;
import ecs.systems.System;
import java.awt.*;

public class GameLoop extends GameScreen {

  public static Graphics2D graphics;
  public static SystemScheduler scheduler = new SystemScheduler();
  ;

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
    scheduler.tick();
  }

  public static void addEntity(Entity entity) {
    Entity.allEntities.put(entity.getUUID(), entity);
  }

  public static void addSystem(System system) {
    scheduler.schedule(system);
  }
}
