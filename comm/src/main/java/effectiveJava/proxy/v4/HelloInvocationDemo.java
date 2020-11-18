package effectiveJava.proxy.v4;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;
import effectiveJava.proxy.v4.jar.InterceptChain;

public class HelloInvocationDemo {
    public static void main(String[] args) {
        InterceptChain chain = new InterceptChain();
        chain.registAll(new LogIntercept(),new LogIntercept(),new LogIntercept());

        HelloService target = new HelloServiceImpl();

        target = (HelloService)chain.wrap(target);
        target.sayHello();

    }
}
