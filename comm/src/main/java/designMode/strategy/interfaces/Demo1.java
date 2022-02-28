package designMode.strategy.interfaces;

public class Demo1 {

    public static void main(String[] args) {
        Duck duck = new Duck(new FreeStroke());
        duck.swim();
    }
}
