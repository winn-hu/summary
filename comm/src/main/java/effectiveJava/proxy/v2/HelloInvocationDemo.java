package effectiveJava.proxy.v2;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;
import effectiveJava.proxy.v2.jar.ProxyInvocation;

import java.util.Arrays;

public class HelloInvocationDemo {
    public static void main(String[] args) {
        HelloService impl = (HelloService) ProxyInvocation.getProxyObject(
                new HelloServiceImpl(),
                Arrays.asList(new BeforeInterceptor()),
                Arrays.asList(new AfterIntercept()));
        impl.sayHello();
    }
}
