package effectiveJava.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LogInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib : Before hello...");
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println("Cglib : After hello...");
        return result;
    }
}
