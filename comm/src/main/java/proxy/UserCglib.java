package proxy;

import org.springframework.cglib.proxy.*;
import sun.plugin2.jvm.RemoteJVMLauncher;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserCglib {

    public static User getCglibByCallbackFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        //创建多个回调函数
        Callback[] callBacks = {
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("开始调用insert");
                    Object result = method.invoke(o, objects);
                    System.out.println("结束调用insert");
                    return result;
                },
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("开始调用get");
                    Object result = method.invoke(o, objects);
                    System.out.println("结束调用get");
                    return result;
                }
        };
        //设置回调函数数组
        enhancer.setCallbacks(callBacks);
        //设置过滤器，筛选回调函数，返回的是callBacks数组的下标  即：insert开头的方法使用第1个回调函数
        enhancer.setCallbackFilter(method -> method.getName().startsWith("insert")  ? 0 : 1);
        //获取代理对象
        User proxy = (User) enhancer.create();
        //调用代理对象的方法
        proxy.out();
        return proxy;
    }

    public static User getCglibByCallbackHelper() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        //创建多个回调函数
        MethodInterceptor insertInterceptor = (o, method, objects, methodProxy) -> {
            System.out.println("开始调用insert");
            Object result = method.invoke(o, objects);
            System.out.println("结束调用insert");
            return result;
        };
        MethodInterceptor getInterceptor = (o, method, objects, methodProxy) -> {
            System.out.println("开始调用get");
            Object result = method.invoke(o, objects);
            System.out.println("结束调用get");
            return result;
        };

        //设置回调函数筛选规则
        CallbackHelper callbackHelper = new CallbackHelper(User.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? insertInterceptor : getInterceptor;
            }
        };

        //设置回调函数数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        //设置过滤器
        enhancer.setCallbackFilter(callbackHelper);

        //获取代理对象
        User proxy = (User) enhancer.create();

        //调用代理对象的方法
        proxy.out();
        return proxy;
    }
}
