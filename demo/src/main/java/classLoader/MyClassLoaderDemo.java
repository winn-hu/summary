package classLoader;

public class MyClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader loader = new MyClassLoader();
        Class<?> aClass = loader.loadClass("Student");
        System.out.println(aClass.getClassLoader());
    }
}
