package effectiveJava.proxy.v3.jar;

public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    default Object wrap(Object target) {
       return ProxyInvocationHandler.getProxyObject(target,this);
    }
}
