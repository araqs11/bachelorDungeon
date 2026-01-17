import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import ecs.Entity;
import ecs.components.DrawComponent;
import ecs.components.PositionComponent;
import ecs.systems.*;
import game.PositionCamera;
import game.entities.Player;
import game.level.LevelLoader;
import game.screens.GameLoop;
import util.InputHandler;

public class Starter {

  public static void main(String[] args) {
    setupClient(args);
    init();
    Game.screens().display(new GameLoop()); // Game loop starts here
    LevelLoader.loadFile("level/default.level");
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
    GameLoop.addSystem(new MoveSystem());
    GameLoop.addSystem(new PlayerInputSystem());
    GameLoop.addSystem(new LevelSystem());
    GameLoop.addSystem(new DrawSystem());
  }

  private static void createTestEntities() {
    GameLoop.addEntity(Player.createPlayer());
    Entity test = new Entity("test");
    test.addComponent(new PositionComponent(50,0));
    test.addComponent(new DrawComponent("entities/enemy.png"));
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
