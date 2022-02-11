package proxy;

public class User implements IUser {

    @Override
    public void out() {
        System.out.println("This is proxy.SystemOut class.");
    }
}
