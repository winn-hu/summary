package effectiveJava.proxy.v2;

public class AfterIntercept implements Interceptor {
    @Override
    public void intercept() {
        System.out.println("AfterIntercept : After Hello....");
    }
}
