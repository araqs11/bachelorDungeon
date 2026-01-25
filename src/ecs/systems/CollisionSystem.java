package ecs.systems;

import ecs.Entity;
import ecs.components.CollisionComponent;
import ecs.components.PositionComponent;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionSystem extends System {

    private HashSet<Pair> collisions;

  public CollisionSystem() {
    super(List.of(CollisionComponent.class, PositionComponent.class));
    collisions = new HashSet<>();
  }

  @Override
  public void execute() {
    getRelevantEntities()
        .flatMap(
            e1 -> getRelevantEntities().filter(e2 -> !e1.equals(e2)).map(e2 -> new Pair(e1, e2)))
        .collect(Collectors.toSet())
        .forEach(
            pair -> {
              // Berechnung ob Objekte Kollidieren
              PositionComponent pc1 = pair.e1.fetch(PositionComponent.class).get();
              CollisionComponent cc1 = pair.e1.fetch(CollisionComponent.class).get();
              PositionComponent pc2 = pair.e2.fetch(PositionComponent.class).get();
              CollisionComponent cc2 = pair.e2.fetch(CollisionComponent.class).get();

              double len1 = Math.abs(pc1.getX() - pc2.getX());
              double len2 = Math.abs(pc1.getY() - pc2.getY());

              if (len1 < (cc1.getWidth() + cc2.getWidth()) / 2
                  && len2 < (cc1.getHeight() + cc2.getHeight()) / 2) {
                  if(collisions.contains(pair)) {
                      cc1.collideHold.accept(pair.e1,pair.e2);
                      cc2.collideHold.accept(pair.e2,pair.e1);
                  } else {
                      cc1.collideEnter.accept(pair.e1,pair.e2);
                      cc2.collideEnter.accept(pair.e2,pair.e1);
                  }
              }
            });
  }

  record Pair(Entity e1, Entity e2) {
    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Pair pair) {
        return e1.equals(pair.e1) && e2.equals(pair.e2) || e1.equals(pair.e2) && e2.equals(pair.e1);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return e1.hashCode() ^ e2.hashCode();
    }

    @Override
    public String toString() {
      return e1.getName() + " - " + e2.getName();
    }
  }
  ;
}
