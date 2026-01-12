package ecs.systems;

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
    if (listOfRelevantComponents.isEmpty()) {
      // all game.entities
      listOfRelevantEntities = new HashSet<>(Entity.allEntities.keySet());
    } else if (listOfRelevantComponents.size() == 1) {
      // only one
      listOfRelevantEntities = Component.listOfEntities.get(listOfRelevantComponents.getFirst());
    } else {
      // x many
      // first check if every Component has been initialized, if yes continue, if no stop here since
      // no fitting entities can be found
      List<Class<? extends Component>> checkingList =
          listOfRelevantComponents.stream().filter(Component.listOfEntities::containsKey).toList();
      if (checkingList.size() == listOfRelevantComponents.size()) {
        try {
          listOfRelevantEntities =
              Component.listOfEntities.get(listOfRelevantComponents.getFirst());
          listOfRelevantComponents.stream()
              .map(klass -> Component.listOfEntities.get(klass))
              .forEach(listOfRelevantEntities::retainAll);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return listOfRelevantEntities;
  }

  public Stream<Entity> getRelevantEntities() {
    return getRelevantUUIDs().stream().map(uuid -> Entity.allEntities.get(uuid));
  }
}
