import ecs.components.DrawComponent;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import ecs.Entity;
import game.entities.Player;
import game.entities.screens.GameLoop;
import ecs.systems.InputSystem;
import ecs.systems.VelocitySystem;
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
