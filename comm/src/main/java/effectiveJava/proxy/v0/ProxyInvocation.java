package effectiveJava.proxy.v0;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class ProxyInvocation implements InvocationHandler {
    /**
     * ����Ԫ
     */
    private Object target;

    public ProxyInvocation(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy ������ʵ��
     * @param method ʵ��Ҫ���õķ���
     * @param args  ʵ��Ҫ���÷����Ĳ�������
     * @return ���ֵ
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("HelloInvocation : Before Hello....");
        Object reslut = method.invoke(target, args);
        System.out.println("HelloInvocation : After Hello....");
        return reslut;
    }

}
