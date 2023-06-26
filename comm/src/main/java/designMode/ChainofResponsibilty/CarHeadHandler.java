package designMode.ChainofResponsibilty;

public class CarHeadHandler extends CarHandler {
    @Override
    public void handler() {
        System.out.println("Car Head Handler...");
        if (this.getNext() != null) {
            this.getNext().handler();
        }
    }
}
