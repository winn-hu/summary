package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisJava {

    Jedis jedis = new Jedis("localhost");

    @Test
    public void testPing() {
        try {
            System.out.println("连接成功："+jedis.ping());
        } catch (Exception e) {
            System.out.println("链接失败，服务未启动。");
        }
    }

    @Test
    public void testString() {
        jedis.set("key", "value");
        System.out.println(jedis.get("key"));
    }
}
