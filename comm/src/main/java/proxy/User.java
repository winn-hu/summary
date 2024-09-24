package proxy;

public class User implements IUser {

    @Override
    public void get() {
        System.out.println("This is proxy.SystemOut class.");
    }

    @Override
    public void insertDB() {
        System.out.println("This is insert.");
    }
}
