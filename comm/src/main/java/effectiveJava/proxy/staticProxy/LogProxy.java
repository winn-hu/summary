package effectiveJava.proxy.staticProxy;

import effectiveJava.proxy.HelloService;

public class LogProxy implements HelloService {

    private HelloService service;

    public LogProxy(HelloService service) {
        this.service = service;
    }

    @Override
    public void sayHello() {
        System.out.println("Static Proxy : Before Hello....");
        service.sayHello();
        System.out.println("Static Proxy : After Hello....");
    }
}
