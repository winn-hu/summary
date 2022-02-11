package proxy;

import java.lang.reflect.Proxy;

public class UserProxy {

    //ע�⣺����ֵ����ӿ�IUser�����ǲ�������ͨ��ʵ����User��ȡ
    public static IUser getProxy() {
        return (IUser) Proxy.newProxyInstance(User.class.getClassLoader(), User.class.getInterfaces(), (proxy, method, args) -> {
            System.out.println("Before Proxy.");
            method.invoke(User.class.newInstance(), args);
            System.out.println("After Proxy.");
            return null;
        });
    }

}
