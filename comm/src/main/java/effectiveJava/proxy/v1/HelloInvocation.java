package effectiveJava.proxy.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloInvocation implements InvocationHandler {
    /**
     * 代理元
     */
    private Object target;

    public HelloInvocation(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 代理类实例
     * @param method 实际要调用的方法
     * @param args  实际要调用方法的参数类型
     * @return 结果值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Hello....");
        Object reslut = method.invoke(target, args);
        System.out.println("after Hello....");
        return reslut;
    }

    public Object getProxyObject() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
