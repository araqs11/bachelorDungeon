package ecs.core;

import java.util.HashSet;
import java.util.UUID;

public abstract class Component {

  public Component() {
    if (!ECS.componentsToEntities.keySet().contains(this.getClass())) {
      ECS.componentsToEntities.put(this.getClass(), new HashSet<>());
    }
  }

}
