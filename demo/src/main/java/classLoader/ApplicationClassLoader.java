package classLoader;

public class ApplicationClassLoader {
    public static void main(String[] args) {
        System.out.println("Class loader : "+ApplicationClassLoader.class.getClassLoader());
        System.out.println("Application Path ; "+System.getProperty("java.class.path").replaceAll(";","\n"));
    }
}
