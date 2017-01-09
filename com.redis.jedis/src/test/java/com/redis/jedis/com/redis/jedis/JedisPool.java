package com.redis.jedis.com.redis.jedis;

import com.redis.jedis.com.redis.jedis.base.Config;

import redis.clients.jedis.Jedis;

public class JedisPool {

    public static void main(String[] args) {
     // 从池中获取一个Jedis对象
        Jedis jedis = Config.pool.getResource();
        String keys = "name";

        // 删数据
        jedis.del(keys);
        // 存数据
        jedis.set(keys, "snowolf");
        // 取数据
        String value = jedis.get(keys);

        System.out.println(value);

        // 释放对象池
//        Config.pool.returnResource(jedis);
    }

}
