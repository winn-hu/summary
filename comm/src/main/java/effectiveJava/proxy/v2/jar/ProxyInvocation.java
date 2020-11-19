package effectiveJava.proxy.v2.jar;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyInvocation implements InvocationHandler {

    private Object target;
    private List<Interceptor> beforeInterceptors;
    private List<Interceptor> afterInterceptors;

    public ProxyInvocation(Object target, List<Interceptor> beforeInterceptors, List<Interceptor> afterInterceptors) {
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

    public static Object getProxyObject(Object target,List<Interceptor> beforeInterceptors,List<Interceptor> afterInterceptors) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new ProxyInvocation(target,beforeInterceptors,afterInterceptors));
    }
}
