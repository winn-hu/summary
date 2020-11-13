package effectiveJava.proxy.v2;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;

import java.util.Arrays;

public class HelloInvocationDemo {
    public static void main(String[] args) {
        HelloInvocation helloInvocation = new HelloInvocation(new HelloServiceImpl(),Arrays.asList(new BeforeInterceptor()),Arrays.asList(new AfterIntercept()));
        HelloService impl = (HelloService)helloInvocation.getProxyObject();
        impl.sayHello();
    }
}
