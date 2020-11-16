package jdk.clone;

public class UserTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        AdminUser winn = new AdminUser(1, "Winn",1);
        Object clone = winn.clone();
        System.out.println(clone.getClass());
        System.out.println(clone);
    }

}