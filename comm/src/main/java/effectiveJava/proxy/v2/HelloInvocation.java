package effectiveJava.proxy.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class HelloInvocation implements InvocationHandler {

    private Object target;
    private List<Interceptor> beforeInterceptors;
    private List<Interceptor> afterInterceptors;

    public HelloInvocation(Object target, List<Interceptor> beforeInterceptors, List<Interceptor> afterInterceptors) {
        this.target = target;
        this.beforeInterceptors = beforeInterceptors;
        this.afterInterceptors = afterInterceptors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        for(Interceptor interceptor : beforeInterceptors) {
            interceptor.intercept();
        }

        Object reslut = method.invoke(target, args);

        for(Interceptor interceptor : afterInterceptors) {
            interceptor.intercept();
        }

        return reslut;
    }

    public Object getProxyObject() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
