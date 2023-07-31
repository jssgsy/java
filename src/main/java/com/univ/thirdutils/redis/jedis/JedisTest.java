package com.univ.thirdutils.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;

/**
 * @author univ
 * date 2023/7/31
 */
public class JedisTest {

    /**
     * 单个实例的用法，不要在实践项目中使用
     */
    @Test
    public void test() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", "univ");
        System.out.println(jedis.get("name"));
        // 判断redis
        if (null != jedis) {
            jedis.close();
        }
    }

    /**
     * 连接池的用法
     */
    @Test
    public void f2() {
        JedisPool pool = new JedisPool("localhost", 6379);
        // try-with-resource
        try (Jedis jedis = pool.getResource()) {
            jedis.set("clientName", "Jedis");
            String value = jedis.get("clientName");
            System.out.println(value);
        }
    }

    /**
     * 此时不需使用try-with-resource
     * 其实只是底层还是try-with-resource
     */
    @Test
    public void f3() {
        JedisPooled pool = new JedisPooled("localhost", 6379);
        pool.set("name2", "Univ2");
        System.out.println(pool.get("name2"));
    }

}
