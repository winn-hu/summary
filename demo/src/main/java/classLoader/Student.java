package classLoader;

import java.util.Random;

public class Student {

    //stitic code will be executed when Student.class is initial.
    static {
        System.out.println("Student.class has been initial.");
    }

    private static int x = 10;

    public static final int CONSTANT = 10;

    public static final int RANDOM = new Random().nextInt(10);
}
