package effectiveJava.proxy.v3.jar;

import java.lang.reflect.Method;

/**
 * 封装拦截的目标，包含：类实例、方法、参数
 * @author Winn
 */
public class Invocation {

    private Object target;
    private Method method;
    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object invoke() throws Throwable {
        return method.invoke(target, args);
    }
}
