package classLoader;

public class ExtClassLoader {
    public static void main(String[] args) {
        System.out.println("Ext path ; "+System.getProperty("java.ext.dirs").replaceAll(";","\n"));
    }
}
