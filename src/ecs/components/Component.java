package ecs.components;

import java.util.HashSet;
import java.util.UUID;

public abstract class Component {
    public HashSet<UUID> listOfEntities = new HashSet<>();

    public HashSet<UUID> getListOfEntities() {
        return listOfEntities;
    }

    public void addToListOfEntities(UUID uuid) {
        listOfEntities.add(uuid);
    }
}
