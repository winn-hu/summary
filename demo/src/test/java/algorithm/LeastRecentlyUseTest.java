package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeastRecentlyUseTest {

    @Test
    public void put() {
        LeastRecentlyUse<String,Integer> lru = new LeastRecentlyUse<>(10);
        lru.put("zore",0);
        lru.put("one",1);
        lru.put("two",2);
        lru.put("three",3);
        lru.put("four",4);
        lru.put("five",5);
        lru.put("six",6);
        lru.put("seven",7);
        lru.put("eight",8);
        lru.put("nine",9);
        lru.put("ten",10);
        lru.put("three",13);
        System.out.println(lru.get("seven")+" : "+lru.get("twenty"));
        lru.print();
    }
}