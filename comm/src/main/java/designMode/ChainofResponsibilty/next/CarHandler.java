package designMode.ChainofResponsibilty.next;

public abstract class CarHandler {

    private CarHandler carHandler;

    //设置下一个节点
    public CarHandler setNext(CarHandler carHandler) {
        this.carHandler = carHandler;
        return this.carHandler;
    }

    //获取下一个节点
    public CarHandler getNext() {
        return this.carHandler;
    }

    //处理当前责任
    public abstract void handler();
}
