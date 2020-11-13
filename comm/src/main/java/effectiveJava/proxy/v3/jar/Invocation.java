package effectiveJava.proxy.v3.jar;

import java.lang.reflect.Method;

/**
 * ��װ���ص�Ŀ�꣬��������ʵ��������������
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
