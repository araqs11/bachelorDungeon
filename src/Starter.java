import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import de.gurkenlabs.litiengine.util.geom.Vector2D;
import ecs.Entity;
import ecs.components.VelocityComponent;
import ecs.systems.InputSystem;
import ecs.systems.VelocitySystem;
import game.entities.Player;
import game.screens.GameLoop;
import util.InputHandler;

public class Starter {

  public static void main(String[] args) {
    setupClient(args);
    init();
    Game.screens().display(new GameLoop()); // Game loop starts here
    Game.start();
  }

  public static void init() {
    InputHandler.init();
    createSystems();
    createTestEntities();
  }

  private static void createSystems() {
    GameLoop.addSystem(new InputSystem());
    GameLoop.addSystem(new VelocitySystem());
  }

  private static void createTestEntities() {
    GameLoop.addEntity(Player.createPlayer());
    Entity test = new Entity("test");
    test.addComponent(new VelocityComponent(new Vector2D(1, 1)));
    GameLoop.addEntity(test);
  }

  private static void setupClient(String[] args) {
    Game.info().setName("Base Dungeon");
    Game.info().setSubTitle("");
    Game.info().setVersion("");
    Game.init(args);
    Game.config().graphics().setDisplayMode(DisplayMode.WINDOWED);
    Game.config().graphics().setResolutionHeight(720);
    Game.config().graphics().setResolutionWidth(1280);
    Game.config().save();
  }
}
