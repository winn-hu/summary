package designMode.strategy.enums;

public class Duck {

    private SkillEnum skill;

    public Duck(SkillEnum skill) {
        this.skill = skill;
    }

    public void swim() {
        skill.getSkill().swim();
    }
}
