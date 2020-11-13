package effectiveJava.proxy.v4.jar;

import java.util.ArrayList;
import java.util.List;

public class InterceptChain {

    private List<Interceptor> interceptorList = new ArrayList<>();

    public void regist(Interceptor interceptor) {
        interceptorList.add(interceptor);
    }

    public void registAll(Interceptor ... interceptors) {
        for(Interceptor interceptor : interceptors) {
            regist(interceptor);
        }
    }

    public Object wrap(Object target) {
        for(Interceptor interceptor : interceptorList) {
            target = interceptor.wrap(target);
        }
        return target;
    }
}
