package proxy;

import java.lang.reflect.Proxy;

public class UserProxy {

    //注意：返回值必须接口IUser，但是参数必须通过实现类User获取
    public static IUser getProxy() {
        return (IUser) Proxy.newProxyInstance(User.class.getClassLoader(), User.class.getInterfaces(), (proxy, method, args) -> {
            System.out.println("Before Proxy.");
            method.invoke(User.class.newInstance(), args);
            System.out.println("After Proxy.");
            return null;
        });
    }

}
