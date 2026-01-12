package ecs;

import ecs.components.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Stream;

public class Entity {

  public static HashMap<UUID, Entity> allEntities = new HashMap<>();

  private ArrayList<Component> components = new ArrayList<>();
  private String name;
  private UUID uuid;

  public Entity(String name) {
    this.name = name;
    this.uuid = UUID.randomUUID();
  }

  public void addComponent(Component component) {
    components.add(component);
    Component.addEntityToInternalComponent(component.getClass(), this.uuid);
  }

  public Stream<Component> getComponents() {
    return components.stream();
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
}
