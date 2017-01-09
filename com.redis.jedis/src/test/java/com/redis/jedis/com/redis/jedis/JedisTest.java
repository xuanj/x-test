package com.redis.jedis.com.redis.jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        String value = jedis.get("key");
        System.out.println(value);
    }

}
