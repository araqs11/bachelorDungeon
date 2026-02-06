package ecs.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/** Collection for all the */
public class ECS {
  public static HashMap<UUID, Entity> allEntities = new HashMap<>();
  public static HashMap<Class<? extends Component>, HashSet<UUID>> componentsToEntities =
      new HashMap<>();
  public static SystemScheduler scheduler = new SystemScheduler();
  private static UUID heroUUID;

  public static void addEntity(Entity entity) {
    ECS.allEntities.put(entity.getUUID(), entity);
  }

  public static void addSystem(System system) {
    scheduler.schedule(system);
  }

  public static void removeEntity(Entity entity) {
    ECS.allEntities.remove(entity.getUUID());
    entity.getComponents().forEach((aClass, component) -> {
      componentsToEntities.get(aClass).remove(entity.getUUID());
    });
  }

  public static void addHero(Entity hero) {
    heroUUID = hero.getUUID();
    addEntity(hero);
  }

  public static Entity getHero() {
    return allEntities.get(heroUUID);
  }

  public static UUID getHeroUUID() {
    return heroUUID;
  }

  public static void removeAllEntitiesButHero() {
    componentsToEntities.values().forEach(set -> set.removeIf(uuid -> !uuid.equals(heroUUID)));
    allEntities.entrySet().removeIf(entry -> !entry.getKey().equals(heroUUID));
  }
}
