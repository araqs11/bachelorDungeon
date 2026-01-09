package screens;

import components.DrawComponent;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import entities.Entity;
import java.awt.*;
import java.util.ArrayList;
import systems.*;

public class GameLoop extends GameScreen {

  private static ArrayList<Entity> allEntities = new ArrayList<>();
  private boolean doInit = true;
  public static Graphics2D graphics;
  public SystemScheduler scheduler;

  public GameLoop() {
    scheduler = new SystemScheduler();
  }

  /**
   * Main game loop.
   *
   * @param g The current graphics object onto which this instance will render its visual contents.
   */
  @Override
  public void render(Graphics2D g) {
    super.render(g);
    if (doInit) init();
    graphics = g;
    scheduler.tick();
  }

  public void addEntity(Entity entity) {
    allEntities.add(entity);
  }

  public void init() {
    createSystems();
    createTestEntities();
    doInit = false;
  }

  private void createSystems() {
    scheduler.schedule(
        () -> {
          java.lang.System.out.println("Test");

          return null;
        });
  }

  private void createTestEntities() {
    Entity player = new Entity("Player");
    player.addComponent(new DrawComponent("player.png"));
    addEntity(player);
  }

  public static ArrayList<Entity> getAllEntities() {
    return allEntities;
  }
}
