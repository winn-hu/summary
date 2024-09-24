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
                    Object result = methodProxy.invokeSuper(o, objects);
                    System.out.println("结束调用insert");
                    return result;
                },
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("开始调用get");
                    Object result = methodProxy.invokeSuper(o, objects);
                    System.out.println("结束调用get");
                    return result;
                }
        };
        //设置回调函数数组
        enhancer.setCallbacks(callBacks);
        //设置过滤器，筛选回调函数，返回的是callBacks数组的下标  即：insert开头的方法使用第1个回调函数
        enhancer.setCallbackFilter(method -> method.getName().startsWith("insert")  ? 0 : 1);
        return (User) enhancer.create();
    }

    public static User getCglibByCallbackHelper() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        //创建多个回调函数
        MethodInterceptor insertInterceptor = (o, method, objects, methodProxy) -> {
            System.out.println("开始调用insert");
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("结束调用insert");
            return result;
        };
        MethodInterceptor getInterceptor = (o, method, objects, methodProxy) -> {
            System.out.println("开始调用get");
            Object result = methodProxy.invokeSuper(o, objects);
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

        return (User) enhancer.create();
    }

    public static void main(String[] args) {
        User proxy = UserCglib.getCglibByCallbackHelper();
        proxy.insertDB();
        System.out.println("-----------------");
        proxy.get();
    }
}
