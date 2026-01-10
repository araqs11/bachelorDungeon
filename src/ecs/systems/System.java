package ecs.systems;

import ecs.components.Component;
import ecs.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class System implements Task {

  private ArrayList<Component> listOfRelevantComponents = new ArrayList<>();

  public System() {

  }

  public System(Component component) {
    listOfRelevantComponents.add(component);
  }

  public System(List components) {
    listOfRelevantComponents.addAll(components);
  }

  @Override
  public Void call() {
    execute();
    return null;
  }

  /** Wrapper for call method. */
  public abstract void execute();

  private HashSet<UUID> getRelevantUUIDs() {
    HashSet<UUID> listOfRelevantEntities;
    if(listOfRelevantComponents.isEmpty()) {
      // all game.entities
      listOfRelevantEntities = new HashSet<>(Entity.allEntities.keySet());
    } else if(listOfRelevantComponents.size()==1) {
      // only one
      listOfRelevantEntities = new HashSet<>(listOfRelevantComponents.getFirst().getListOfEntities());
    } else {
      // x many
      // geht incht weil nicht reTainAll genutzt
      listOfRelevantEntities = new HashSet<>(listOfRelevantComponents.getFirst().getListOfEntities());
      listOfRelevantComponents.stream().map(Component::getListOfEntities).forEach(listOfRelevantEntities::retainAll
      );
    }

    return listOfRelevantEntities;
  }

  public Stream<Entity> getRelevantEntities() {
    return getRelevantUUIDs().stream()
            .map(uuid->Entity.allEntities.get(uuid));
  }
}
