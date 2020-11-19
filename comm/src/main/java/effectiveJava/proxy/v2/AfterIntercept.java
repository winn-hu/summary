package effectiveJava.proxy.v2;

import effectiveJava.proxy.v2.jar.Interceptor;

public class AfterIntercept implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("AfterIntercept : After Hello....");
    }
}
