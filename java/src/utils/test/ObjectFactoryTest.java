package utils.test;

import utils.ObjectFactory;

import java.util.HashMap;
import java.util.Set;

public class ObjectFactoryTest {

    public static void main(String[] args) {
        Object o = ObjectFactory.createObject("utils.test.Person");
        ObjectFactory.setValue(o, "name", "TTT-1");
        ObjectFactory.setValue(o, "gender", "1", "int");
        Object o2 = ObjectFactory.createObject("utils.test.Person");
        ObjectFactory.dataTransfer(o, o2);
        System.out.println(o2);
        System.out.println("-------------------------------");
        HashMap map = ObjectFactory.getcolValueMap(o);
        Set keySet = map.keySet();
        keySet.forEach(key -> System.out.println(key + " ： "+ map.get(key)));
        System.out.println("-------------------------------");
        Object name = ObjectFactory.execMethod(o, "getName");
        System.out.println("exec result : "+name);
    }
}
