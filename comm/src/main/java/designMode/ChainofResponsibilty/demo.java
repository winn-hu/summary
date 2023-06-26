package designMode.ChainofResponsibilty;

public class demo {

    public static void main(String[] args) {
        CarHandler headHandler = new CarHeadHandler();
        CarHandler bodyHandler = new CarBodyHandler();
        CarHandler tailHandler = new CarTailHandler();
        //指定责任链顺序
        bodyHandler.setNext(headHandler).setNext(tailHandler);
        //启动第一个节点
        bodyHandler.handler();

    }
}
