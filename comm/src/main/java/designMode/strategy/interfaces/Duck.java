package designMode.strategy.interfaces;

public class Duck {

    Skill skill;

    public Duck(Skill skill) {
        this.skill = skill;
    }

    public void swim() {
        skill.swim();
    }
}
