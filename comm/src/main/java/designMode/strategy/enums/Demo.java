package designMode.strategy.enums;

public class Demo {

    public static void main(String[] args) {
        Duck duck = new Duck(SkillEnum.BREAST_STROKE);
        duck.swim();
    }
}
