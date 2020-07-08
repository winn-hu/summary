package jdk;

public class ProjectPath {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String home = System.getProperty("user.home");
        String classPath = System.getProperty("java.class.path");
        System.out.println(path+" : "+home+" : "+classPath);
    }
}
