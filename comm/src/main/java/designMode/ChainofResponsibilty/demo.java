package designMode.ChainofResponsibilty;

public class demo {

    public static void main(String[] args) {
        CarHandler headHandler = new CarHeadHandler();
        CarHandler bodyHandler = new CarBodyHandler();
        CarHandler tailHandler = new CarTailHandler();
        //ָ��������˳��
        bodyHandler.setNext(headHandler).setNext(tailHandler);
        //������һ���ڵ�
        bodyHandler.handler();

    }
}
