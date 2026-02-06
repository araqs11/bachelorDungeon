package ecs.components;

import game.skills.Skill;

import java.util.ArrayList;

public class SkillComponent extends Component {

    private ArrayList<Skill> skills;
    private int currentSkill = 0;

    public SkillComponent() {
        skills = new ArrayList<>();
    }

    public void addSkill(Skill skill) {
        skills.add(skill);

    }

    public Skill getCurrentSkill() {
        return skills.get(currentSkill);
    }

    /**
     * Goes to the next skill, resets to 0 if out of bounds.
     */
    public void nextSkill() {
        currentSkill++;
        if(currentSkill >= skills.size()) {
            currentSkill=0;
        }
    }
}
