import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import ecs.components.PlayerComponent;
import ecs.core.ECS;
import ecs.systems.*;
import game.AudioPlayer;
import game.EntityFactory;
import game.GameLoop;
import game.level.LevelLoader;
import game.level.levellogic.LevelOne;
import game.level.levellogic.EmptyLevel;

import java.util.List;
import java.util.logging.Level;

import game.level.levellogic.LevelTwo;
import util.input.InputHandler;

public class Starter {

  public static void main(String[] args) {
    setupClient(args);
    init();
    Game.graphics().setBaseRenderScale(1f);
    Game.screens().display(new GameLoop()); // Game loop starts here
    LevelLoader.loadLevel("level/levelOne.level", new LevelOne());
    LevelLoader.loadLevel("level/levelTwo.level", new LevelTwo());
    LevelLoader.loadNextLevel();
    Game.start();
  }

  public static void init() {
    InputHandler.init();
    createSystems();
    createTestEntities();
    AudioPlayer.setVolume(0.1f);
  }

  private static void createSystems() {
    ECS.addSystem(new InputSystem());
    ECS.addSystem(new VelocitySystem());
    ECS.addSystem(new MoveSystem());
    ECS.addSystem(new PlayerInputSystem());
    ECS.addSystem(new CollisionSystem());
    ECS.addSystem(new ProjectileSystem());
    ECS.addSystem(new HealthSystem());
    ECS.addSystem(new LevelSystem());
    ECS.addSystem(new DrawSystem());
  }

  private static void createTestEntities() {
    ECS.addHero(EntityFactory.createPlayer(1,1));
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
