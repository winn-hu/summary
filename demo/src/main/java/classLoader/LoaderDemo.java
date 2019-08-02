package classLoader;

import org.junit.Test;

/**
 * Demo whether init class or class
 */
public class LoaderDemo {

    /**
     * new object will init Student.class
     */
    @Test
    public void StudentTest(){
        new Student();
    }

    /**
     * create array by using class will not init Student.class.
     */
    @Test
    public void ArrayTest(){
        Student[] students = new Student[10];
        System.out.println(students.length);
    }

    /**
     * invoke static constract will not init Student.class.
     */
    @Test
    public void ConstractTest(){
        System.out.println("Constract : "+Student.CONSTANT);
    }

    /**
     *Student.class will be initted due to RANDOM is not certainly
     */
    @Test
    public void RandomTest(){
        System.out.println("Random : "+Student.RANDOM);
    }
}
