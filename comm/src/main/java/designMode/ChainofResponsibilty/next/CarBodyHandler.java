package designMode.ChainofResponsibilty.next;

public class CarBodyHandler extends CarHandler {
    @Override
    public void handler() {
        System.out.println("Car Body Handler...");
        if (this.getNext() != null) {
            this.getNext().handler();
        }
    }
}
