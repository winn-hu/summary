package effectiveJava.singleton;

public class UserDemo {

    public static void main(String[] args) {
        System.out.println(User.getInstance());
        System.out.println(User.getInstance());
        System.out.println(User.getInstance());
        System.out.println(User.getInstance());
    }
}
