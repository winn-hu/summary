package effectiveJava.proxy.v4;

import effectiveJava.proxy.v4.jar.Interceptor;
import effectiveJava.proxy.v4.jar.Invocation;

public class LogIntercept implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("LogIntercept : After Hello....");
        Object invoke = invocation.invoke();
        System.out.println("LogIntercept : After Hello....");
        return invoke;
    }
}
