package utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

public class JsonUtilTest {

    @Test
    public void map2jsonTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,10,1,23,59,59);
        Date endDate = calendar.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("INTEGER","1");
        map.put("START_TIME", LocalDateTime.now());
        map.put("END_TIME",endDate);

        String json = JsonUtil.map2json(map);
        System.out.println(json);
    }

    @Test
    public void json2mapTest() {
        String json = "{\"START_TIME\":\"2019-07-01 16:02:36\",\"END_TIME\":\"2019-11-01 23:59:59\",\"INTEGER\":\"1\"}";
        Map map = JsonUtil.json2map(json);
        map.keySet().forEach(key -> System.out.println(key+":"+map.get(key)));
    }

    @Test
    public void list2jsonTest(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,11,1,23,59,59);
        Date endDate = calendar.getTime();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("INTEGER","1");
        map1.put("START_TIME", LocalDateTime.now());
        map1.put("END_TIME",endDate);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("INTEGER","2");
        map2.put("START_TIME", LocalDateTime.now());
        map2.put("END_TIME",endDate);

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        String json = JsonUtil.list2json(list);
        System.out.println(json);
    }

    @Test
    public void json2listTest(){
        String json = "[{\"START_TIME\":\"2019-07-01 16:20:02\",\"END_TIME\":\"2019-12-01 23:59:59\",\"INTEGER\":\"1\"},{\"START_TIME\":\"2019-07-01 16:20:02\",\"INTEGER\":\"2\"}]";
        JsonUtil.json2list(json).forEach(object -> {
            Map map = (Map) object;
            map.keySet().forEach(key -> System.out.println(key+":"+map.get(key)));
        });
    }

    @Test
    public void getValueTest(){
        String json = "{\"START_TIME\":\"2019-07-01 16:02:36\",\"END_TIME\":\"2019-11-01 23:59:59\",\"INTEGER\":\"1\"}";
        Assert.assertEquals(JsonUtil.getValue(json,"END_TIME",String.class),"2019-11-01 23:59:59");
    }

}