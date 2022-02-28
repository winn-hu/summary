package designMode.strategy.interfaces;

public class FreeStroke implements Skill {
    @Override
    public void swim() {
        System.out.println("I can free stroke.");
    }
}
