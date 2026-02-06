package game.skills;

import ecs.ECS;
import ecs.Entity;
import ecs.components.DrawComponent;
import ecs.components.PositionComponent;
import ecs.components.ProjectileComponent;
import ecs.components.VelocityComponent;
import util.Util;
import util.Vector;
import util.statemaschine.State;
import util.statemaschine.StateMachine;

public class FireballSkill extends Skill {


    @Override
    public void execute(Entity caster) {
        PositionComponent pc = caster.fetch(PositionComponent.class).get();

        Entity fireball = new Entity("fireBall");
        StateMachine sm = new StateMachine();
        sm.addState( new State("idle","entities/fire.png"));
        fireball.addComponent(new DrawComponent(sm));
        fireball.addComponent(new PositionComponent(pc.getX(), pc.getY()));
        fireball.addComponent(new VelocityComponent(Vector.ZERO));

        Vector endPoint = pc.getPosition().add(Util.getRelativeCursorPosition());
        fireball.addComponent(new ProjectileComponent(3f,1f,pc.getPosition(), endPoint));
        ECS.addEntity(fireball);

    }
}
