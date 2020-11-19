package effectiveJava.proxy.cglib;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class CglibDemo {
    public static void main(String[] args) {
        //����Enhancer����������JDK��̬�����Proxy��
        Enhancer enhancer = new Enhancer();
        //���ø���
        enhancer.setSuperclass(HelloServiceImpl.class);
        //���ûص���������������
        enhancer.setCallback(new LogInterceptor());
        //����������ʵ��
        HelloService service = (HelloService)enhancer.create();
        service.sayHello();
    }
}
