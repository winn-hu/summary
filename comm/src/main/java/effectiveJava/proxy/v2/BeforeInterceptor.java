package effectiveJava.proxy.v2;

public class BeforeInterceptor implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("BeforeInterceptor : Before Hello....");
    }
}
