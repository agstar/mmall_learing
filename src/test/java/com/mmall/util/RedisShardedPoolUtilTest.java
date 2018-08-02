package com.mmall.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static org.junit.Assert.*;

public class RedisShardedPoolUtilTest {

    @Test
    public void expire() {
    }

    @Test
    public void setEx() {
    }

    @Test
    public void set() {
    }

    @Test
    public void get() {
    }

    @Test
    public void del() {
    }


    @Test
    public void testCOnnection(){
        JedisPool jedisPool = new JedisPool("192.168.237.7",6379);

        Jedis resource = jedisPool.getResource();
        System.out.println(resource.ping());

    }


}