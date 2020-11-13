package effectiveJava.proxy;

public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("Hello Proxy.");
    }
}
