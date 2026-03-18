package ecs.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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
    entity
        .getComponents()
        .forEach(
            (aClass, component) -> {
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

  public static void addEntityToInternalComponent(Class<? extends Component> klass, UUID uuid) {
    componentsToEntities.get(klass).add(uuid);
  }

  private static HashSet<UUID> getRelevantUUIDs(List<Class<? extends Component>> listOfRelevantComponents) {
    HashSet<UUID> listOfRelevantEntities = new HashSet<>();
    // first check if every Component has been initialized, if yes continue, if no stop here since
    // no fitting entities can be found
    List<Class<? extends Component>> checkingList =
            listOfRelevantComponents.stream().filter(ECS.componentsToEntities::containsKey).toList();
    if (checkingList.size() == listOfRelevantComponents.size()) {

      if (listOfRelevantComponents.isEmpty()) {
        listOfRelevantEntities = new HashSet<>(ECS.allEntities.keySet());
      } else if (listOfRelevantComponents.size() == 1) {
        listOfRelevantEntities = ECS.componentsToEntities.get(listOfRelevantComponents.getFirst());
      } else {
        listOfRelevantEntities = ECS.componentsToEntities.get(listOfRelevantComponents.getFirst());
        listOfRelevantComponents.stream()
                .map(klass -> ECS.componentsToEntities.get(klass))
                .forEach(listOfRelevantEntities::retainAll);
      }
    }
    return listOfRelevantEntities;
  }

  public static Stream<Entity> getRelevantEntities(List<Class<? extends Component>> components) {
    return ECS.getRelevantUUIDs(components).stream().map(uuid -> ECS.allEntities.get(uuid));
  }

}
