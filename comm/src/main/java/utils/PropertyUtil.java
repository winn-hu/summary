package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public static Properties instance(String url){
        Properties properties = new Properties();
        try {
            properties.load(PropertyUtil.class.getResourceAsStream(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
