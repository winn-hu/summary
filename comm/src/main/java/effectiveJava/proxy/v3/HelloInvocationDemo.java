package effectiveJava.proxy.v3;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;

public class HelloInvocationDemo {
    public static void main(String[] args) {
        HelloService target = new HelloServiceImpl();
        //拦截器调用两次，日志输出两遍
        target = (HelloService)new LogIntercept().wrap(target);
        target = (HelloService)new LogIntercept().wrap(target);
        target.sayHello();
    }
}
