package ecs.core;

import java.util.HashSet;

public abstract class Component {

  public Component() {
    if (!ECS.componentsToEntities.keySet().contains(this.getClass())) {
      ECS.componentsToEntities.put(this.getClass(), new HashSet<>());
    }
  }
}
