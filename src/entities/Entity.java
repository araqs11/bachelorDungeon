package entities;

import components.Component;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Entity extends de.gurkenlabs.litiengine.entities.Entity {

  private ArrayList<Component> components = new ArrayList<>();

  private String name;

  public Entity(String name) {
    this.name = name;
  }

  public void addComponent(Component component) {
    components.add(component);
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
}
