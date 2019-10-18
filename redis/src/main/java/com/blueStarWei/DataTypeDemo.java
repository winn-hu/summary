package com.blueStarWei;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.*;

public class DataTypeDemo {

    private static Jedis jedis = new Jedis("localhost");

    @Test
    public void testString() {
        jedis.set("name","winn");
        System.out.println(jedis.get("name"));

        //拼接
        jedis.append("name", " is my lover");
        System.out.println(jedis.get("name"));

        jedis.del("name");
        System.out.println(jedis.get("name"));

        //设置多个键值对
        jedis.mset("name","winn","age","27","qq","174754613");
        //进行加1操作
        jedis.incr("age");
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));//执行结果：winn-28-174754613
    }

    @Test
    public void testList(){
        if(jedis.keys("*") != null){
            jedis.flushDB();
        }
        //rpush ： 在表尾添加数据
        jedis.rpush("db","This","is","redis");
        jedis.rpush("db","demo");
        //lpush ： 在表头添加数据
        jedis.lpush("db","Begin : ");

        //lrange ： 获取链表中的值 、 llen ： 获取链表长度
        System.out.println(jedis.lrange("db", 0, jedis.llen("db")));
        //-1表示最后一个元素
        System.out.println(jedis.lrange("db", 0, -1));
        //排序 : 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
        //System.out.println(jedis.sort("db"));
    }

    @Test
    public void testHash(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");

        jedis.hmset("user",map);

        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //null

        //hlen 返回key中的field-value个数
        System.out.println(jedis.hlen("user")); //2
        //exists 判断key是否存在
        System.out.println(jedis.exists("user"));//true
        //hkeys 返回所有的field
        System.out.println(jedis.hkeys("user"));//[name, qq]
        //hvals 返回所有的value
        System.out.println(jedis.hvals("user"));//[xinxin, 123456]

        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }
    }

    @Test
    public void testSet(){
        if(jedis.keys("*") != null){
            jedis.flushDB();
        }
        //添加
        jedis.sadd("user","liuling");
        jedis.sadd("user","xinxin");
        jedis.sadd("user","ling");
        jedis.sadd("user","zhangxinxin");
        jedis.sadd("user","who");
        //srem : 移除value
        jedis.srem("user","who");
        System.out.println(jedis.smembers("user"));//获取所有加入的value
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user"));//随机返回一个value
        System.out.println(jedis.scard("user"));//返回集合的元素个数

        jedis.sadd("customer","ling");
        jedis.sadd("customer","xinxin");
        jedis.sadd("customer","winn");
        jedis.sadd("customer","nicole");
        jedis.sadd("customer","sam");
        //并集
        System.out.println(jedis.sunion("user","customer"));
        //差集 ： 返回user中customer不包含的value
        System.out.println(jedis.sdiff("user","customer"));
        //交集
        System.out.println(jedis.sinter("user","customer"));
    }

    @Test
    public void testZSet(){
        jedis.zadd("user",1,"winn");
        jedis.zadd("user",2,"sam");
        jedis.zadd("user",5,"nicole");
        jedis.zadd("user",4,"roy");
        Set<String> user = jedis.zrange("user", 0, -1);
        System.out.println(user);
    }

    /**
     * 使用事务
     * Redis事务可以理解为一个打包的批量执行脚本，中间某条指令的失败不会导致前面已做指令的回滚，也不会造成后续的指令不做。
     *
     */
    @Test
    public void testTrans() {
        //开启事务
        Transaction tx = jedis.multi();
        for (int i = 0; i < 1000; i++) {
            tx.set("t" + i, "t" + i);
        }
        //执行事务
        tx.exec();
        System.out.println(jedis.get("t100"));
    }

    /**
     * 清除当前数据库中的数据
     */
    @Test
    public void flushDB() {
        jedis.flushDB();
    }
}
