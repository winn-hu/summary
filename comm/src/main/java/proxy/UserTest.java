package proxy;

public class UserTest {

    public static void main(String[] args) {
        IUser proxy = UserProxy.getProxy();
        proxy.out();
    }

}
