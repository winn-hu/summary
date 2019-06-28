package utils;

import org.junit.Test;

import java.util.Properties;

public class PropertyUtilTest {

    @Test
    public void getProperty(){
        Properties properties = PropertyUtil.instance("/demo.properties");
        String context = properties.getProperty("context");
        String name = properties.getProperty("name");
        String age = properties.getProperty("age");
        String say = String.format(context, name, age);
        System.out.println(say);
    }
}