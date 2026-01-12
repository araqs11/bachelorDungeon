package ecs.components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public abstract class Component {
  public static HashMap<Class<? extends Component>, HashSet<UUID>> listOfEntities = new HashMap<>();

  public Component() {
    if (!listOfEntities.keySet().contains(this.getClass())) {
      listOfEntities.put(this.getClass(), new HashSet<>());
      // System.out.println("new Component registered:" + this.getClass().getName());
    }
  }

  public static void addEntityToInternalComponent(Class<? extends Component> klass, UUID uuid) {
    listOfEntities.get(klass).add(uuid);
  }
}
