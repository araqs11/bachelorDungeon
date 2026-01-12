package ecs.systems;

import ecs.ECS;
import ecs.Entity;
import ecs.components.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class System implements Task {

  private ArrayList<Class<? extends Component>> listOfRelevantComponents = new ArrayList<>();

  public System() {}

  public System(Class<? extends Component> component) {
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
    HashSet<UUID> listOfRelevantEntities = new HashSet<>();
    // first check if every Component has been initialized, if yes continue, if no stop here since
    // no fitting entities can be found
    List<Class<? extends Component>> checkingList =
        listOfRelevantComponents.stream().filter(ECS.componentsToEntities::containsKey).toList();
    if (checkingList.size() == listOfRelevantComponents.size()) {

      if (listOfRelevantComponents.isEmpty()) {
        listOfRelevantEntities = new HashSet<>(ECS.allEntities.keySet());
      } else if (listOfRelevantComponents.size() == 1) {
        listOfRelevantEntities = ECS.componentsToEntities.get(listOfRelevantComponents.getFirst());
      } else {
        listOfRelevantEntities = ECS.componentsToEntities.get(listOfRelevantComponents.getFirst());
        listOfRelevantComponents.stream()
            .map(klass -> ECS.componentsToEntities.get(klass))
            .forEach(listOfRelevantEntities::retainAll);
      }
    }
    return listOfRelevantEntities;
  }

  public Stream<Entity> getRelevantEntities() {
    return getRelevantUUIDs().stream().map(uuid -> ECS.allEntities.get(uuid));
  }
}
