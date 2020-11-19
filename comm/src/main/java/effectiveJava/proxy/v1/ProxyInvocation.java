package effectiveJava.proxy.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        System.out.println("Before Hello....");
        Object reslut = method.invoke(target, args);
        System.out.println("after Hello....");
        return reslut;
    }

    public static Object getProxyObject(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new ProxyInvocation(target));
    }
}
