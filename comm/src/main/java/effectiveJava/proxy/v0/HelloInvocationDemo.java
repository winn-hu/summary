package effectiveJava.proxy.v0;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;

import java.lang.reflect.Proxy;

/**
 * ͨ��Proxy.newProxyInstance��ClassLoader loader, Class<?>[] interfaces, InvocationHandler h��������������ʵ��
 */
public class HelloInvocationDemo {
    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        HelloInvocation helloInvocation = new HelloInvocation(helloService);
        HelloService impl = (HelloService)Proxy.newProxyInstance(
                helloService.getClass().getClassLoader(),
                helloService.getClass().getInterfaces(),
                helloInvocation);
        impl.sayHello();
    }
}

