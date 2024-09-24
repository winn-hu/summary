package designMode.ChainofResponsibilty.next;

public abstract class CarHandler {

    private CarHandler carHandler;

    //������һ���ڵ�
    public CarHandler setNext(CarHandler carHandler) {
        this.carHandler = carHandler;
        return this.carHandler;
    }

    //��ȡ��һ���ڵ�
    public CarHandler getNext() {
        return this.carHandler;
    }

    //����ǰ����
    public abstract void handler();
}
