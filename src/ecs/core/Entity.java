package ecs.core;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class Entity {

  private HashMap<Class<? extends Component>, Component> components = new HashMap<>();
  private String name;
  private UUID uuid;

  public Entity(String name) {
    this.name = name;
    this.uuid = UUID.randomUUID();
  }

  public void addComponent(Component component) {
    components.put(component.getClass(), component);
    ECS.addEntityToInternalComponent(component.getClass(), this.uuid);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getUUID() {
    return uuid;
  }

  public <T extends Component> Optional<T> fetch(Class<T> klass) {
    return Optional.ofNullable(klass.cast(components.get(klass)));
  }

  public HashMap<Class<? extends Component>, Component> getComponents() {
    return components;
  }
}
