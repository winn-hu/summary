package designMode.ChainofResponsibilty;

public class CarTailHandler extends CarHandler {
    @Override
    public void handler() {
        System.out.println("Car Tail Handler...");
        if (this.getNext() != null) {
            this.getNext().handler();
        }
    }
}
