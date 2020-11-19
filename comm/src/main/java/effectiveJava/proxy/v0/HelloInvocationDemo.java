package effectiveJava.proxy.v0;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;

import java.lang.reflect.Proxy;

/**
 * 通过Proxy.newProxyInstance（ClassLoader loader, Class<?>[] interfaces, InvocationHandler h）创建代理对象的实例
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

