package effectiveJava.proxy.v2;

import effectiveJava.proxy.v2.jar.Interceptor;

public class BeforeInterceptor implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("BeforeInterceptor : Before Hello....");
    }
}
