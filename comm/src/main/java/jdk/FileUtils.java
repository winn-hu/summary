package jdk;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class FileUtils {

    /**
     *
     * @param fileName 不带后缀名[只能读取properties文件]
     */
    public static void readPropertiesFile(String fileName) {
        ResourceBundle bundle = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            String value = bundle.getString(key);
            System.out.println(key +" : "+value);
        }
    }
}
