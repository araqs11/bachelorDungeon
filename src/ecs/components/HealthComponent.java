package ecs.components;

import ecs.core.Component;
import ecs.core.ECS;
import ecs.core.Entity;

import java.util.function.Consumer;

public class HealthComponent extends Component {

    private int currentHealth;
    private int maxHealth;
    private Consumer<Entity> onDeath = (e) ->{};
    private boolean renderHealth;

    public HealthComponent(int currentHealth, int maxHealth, Consumer<Entity> onDeath) {
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.onDeath = onDeath;
        this.renderHealth = false;
    }

    public HealthComponent(int health, Consumer<Entity> onDeath) {
        this(health,health, onDeath);
    }

    public HealthComponent(int health) {
        this(health, ECS::removeEntity);
    }


    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void heal(int amount) {
        currentHealth += amount;
        if (currentHealth> maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public void reduce(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Consumer<Entity> getOnDeath() {
        return onDeath;
    }

    public void setRenderHealth(boolean renderHealth) {
        this.renderHealth = renderHealth;
    }

    public boolean isRenderHealth() {
        return renderHealth;
    }
}
