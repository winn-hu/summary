package proxy;

import org.springframework.cglib.proxy.*;
import sun.plugin2.jvm.RemoteJVMLauncher;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserCglib {

    public static User getCglibByCallbackFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        //��������ص�����
        Callback[] callBacks = {
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("��ʼ����insert");
                    Object result = methodProxy.invokeSuper(o, objects);
                    System.out.println("��������insert");
                    return result;
                },
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("��ʼ����get");
                    Object result = methodProxy.invokeSuper(o, objects);
                    System.out.println("��������get");
                    return result;
                }
        };
        //���ûص���������
        enhancer.setCallbacks(callBacks);
        //���ù�������ɸѡ�ص����������ص���callBacks������±�  ����insert��ͷ�ķ���ʹ�õ�1���ص�����
        enhancer.setCallbackFilter(method -> method.getName().startsWith("insert")  ? 0 : 1);
        return (User) enhancer.create();
    }

    public static User getCglibByCallbackHelper() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        //��������ص�����
        MethodInterceptor insertInterceptor = (o, method, objects, methodProxy) -> {
            System.out.println("��ʼ����insert");
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("��������insert");
            return result;
        };
        MethodInterceptor getInterceptor = (o, method, objects, methodProxy) -> {
            System.out.println("��ʼ����get");
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("��������get");
            return result;
        };

        //���ûص�����ɸѡ����
        CallbackHelper callbackHelper = new CallbackHelper(User.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? insertInterceptor : getInterceptor;
            }
        };

        //���ûص���������
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        //���ù�����
        enhancer.setCallbackFilter(callbackHelper);

        return (User) enhancer.create();
    }

    public static void main(String[] args) {
        User proxy = UserCglib.getCglibByCallbackHelper();
        proxy.insertDB();
        System.out.println("-----------------");
        proxy.get();
    }
}
