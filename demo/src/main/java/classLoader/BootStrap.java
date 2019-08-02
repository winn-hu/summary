package classLoader;

public class BootStrap {
    public static void main(String[] args) {
        //bootstrap can not get reference
        System.out.println("Bootstrap ref : "+String.class.getClassLoader());
        System.out.println("Bootstrap load path : "+System.getProperty("sun.boot.class.path").replaceAll(";","\n"));
    }
}
