import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import ecs.ECS;
import ecs.systems.*;
import game.entities.EntityFactory;
import game.level.DefaultLevel;
import game.level.EmptyLevel;
import game.level.LevelLoader;
import game.screens.GameLoop;
import java.util.logging.Level;
import util.input.InputHandler;

public class Starter {

  public static void main(String[] args) {
    setupClient(args);
    init();
    Game.graphics().setBaseRenderScale(1f);
    Game.screens().display(new GameLoop()); // Game loop starts here
    LevelLoader.loadLevel("level/default.level", new DefaultLevel());
    LevelLoader.loadLevel("level/default1.level", new EmptyLevel());
    LevelLoader.loadNextLevel();
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
    ECS.addSystem(new CollisionSystem());
    ECS.addSystem(new ProjectileSystem());
    ECS.addSystem(new LevelSystem());
    ECS.addSystem(new DrawSystem());
    ECS.addSystem(new TestingSystem());
  }

  private static void createTestEntities() {
    ECS.addHero(EntityFactory.createPlayer());
  }

  private static void setupClient(String[] args) {
    Game.log().setLevel(Level.WARNING);
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
