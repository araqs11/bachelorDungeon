package ecs;

import ecs.components.Component;
import ecs.systems.System;
import ecs.systems.SystemScheduler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/** Collection for all the */
public class ECS {
  public static HashMap<UUID, Entity> allEntities = new HashMap<>();
  public static HashMap<Class<? extends Component>, HashSet<UUID>> componentsToEntities =
      new HashMap<>();
  public static SystemScheduler scheduler = new SystemScheduler();



  public static void addEntity(Entity entity) {
    ECS.allEntities.put(entity.getUUID(), entity);
  }

  public static void addSystem(System system) {
    scheduler.schedule(system);
  }
}
