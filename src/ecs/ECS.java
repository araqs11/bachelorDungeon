package ecs;

import ecs.components.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/** Collection for all the */
public class ECS {
  public static HashMap<UUID, Entity> allEntities = new HashMap<>();
  public static HashMap<Class<? extends Component>, HashSet<UUID>> componentsToEntities =
      new HashMap<>();
}
