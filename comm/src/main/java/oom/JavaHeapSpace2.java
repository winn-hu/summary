package oom;

import java.util.HashMap;
import java.util.Map;

/**
 * ÄÚ´æĞ¹Â¶
 */
public class JavaHeapSpace2 {

    public static void main(String[] args) {
        Map<Key,String> map = new HashMap<>();
        while (true) {
            Key key = new Key();
            if(!map.containsKey(key)) {
                map.put(key, "Java Overhead");
                System.out.println(key);
            }
        }

    }
}

class Key {

}
