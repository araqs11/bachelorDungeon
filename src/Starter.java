import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import ecs.ECS;
import ecs.Entity;
import ecs.components.DrawComponent;
import ecs.components.PositionComponent;
import ecs.systems.*;
import game.entities.Player;
import game.level.LevelLoader;
import game.screens.GameLoop;
import util.InputHandler;

public class Starter {

  public static void main(String[] args) {
    setupClient(args);
    init();
    Game.graphics().setBaseRenderScale(1f);
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
    ECS.addSystem(new InputSystem());
    ECS.addSystem(new VelocitySystem());
    ECS.addSystem(new MoveSystem());
    ECS.addSystem(new PlayerInputSystem());
    ECS.addSystem(new LevelSystem());
    ECS.addSystem(new DrawSystem());
  }

  private static void createTestEntities() {
    ECS.addEntity(Player.createPlayer());
    Entity test = new Entity("test");
    test.addComponent(new PositionComponent(32, 16));
    test.addComponent(new DrawComponent("entities/enemy.png"));
    ECS.addEntity(test);
  }

  private static void setupClient(String[] args) {
    Game.info().setName("Base Dungeon");
    Game.info().setSubTitle("");
    Game.info().setVersion("");
    Game.init(args);
    Game.config().client().setMaxFps(60);
    Game.config().graphics().setDisplayMode(DisplayMode.WINDOWED);
    Game.config().graphics().setResolutionHeight(720);
    Game.config().graphics().setResolutionWidth(1280);
    Game.config().save();
  }
}
