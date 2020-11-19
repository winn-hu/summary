package effectiveJava.proxy.cglib;

import effectiveJava.proxy.HelloService;
import effectiveJava.proxy.HelloServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class CglibDemo {
    public static void main(String[] args) {
        //创建Enhancer对象，类似于JDK动态代理的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(HelloServiceImpl.class);
        //设置回调函数（拦截器）
        enhancer.setCallback(new LogInterceptor());
        //创建代理类实例
        HelloService service = (HelloService)enhancer.create();
        service.sayHello();
    }
}
