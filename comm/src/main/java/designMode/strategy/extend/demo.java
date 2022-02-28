package designMode.strategy.extend;

public class demo {

    public static void main(String[] args) {
        Duck duck = new FreeStrokeDuck();
        duck.swim();
    }
}
