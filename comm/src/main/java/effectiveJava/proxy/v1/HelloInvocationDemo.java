package effectiveJava.proxy.v1;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;

public class HelloInvocationDemo {
    public static void main(String[] args) {
        HelloService impl = (HelloService)ProxyInvocation.getProxyObject(new HelloServiceImpl());
        impl.sayHello();
    }
}
