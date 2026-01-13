package ecs.components;

import ecs.ECS;
import java.util.HashSet;
import java.util.UUID;

public abstract class Component {

  public Component() {
    if (!ECS.componentsToEntities.keySet().contains(this.getClass())) {
      ECS.componentsToEntities.put(this.getClass(), new HashSet<>());
    }
  }

  public static void addEntityToInternalComponent(Class<? extends Component> klass, UUID uuid) {
    ECS.componentsToEntities.get(klass).add(uuid);
  }
}
