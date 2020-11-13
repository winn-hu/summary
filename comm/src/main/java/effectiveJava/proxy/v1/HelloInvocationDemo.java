package effectiveJava.proxy.v1;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;

public class HelloInvocationDemo {
    public static void main(String[] args) {
        HelloInvocation helloInvocation = new HelloInvocation(new HelloServiceImpl());
        HelloService impl = (HelloService)helloInvocation.getProxyObject();
        impl.sayHello();
    }
}
