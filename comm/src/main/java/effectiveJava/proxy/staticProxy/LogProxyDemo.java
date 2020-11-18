package effectiveJava.proxy.staticProxy;

import effectiveJava.proxy.HelloServiceImpl;

public class LogProxyDemo {
    public static void main(String[] args) {
        LogProxy logProxy = new LogProxy(new HelloServiceImpl());
        logProxy.sayHello();
    }
}
