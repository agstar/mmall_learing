/*
package com.mmall.util;

import com.mmall.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

@Slf4j
public class RedisPoolUtil {


    */
/**
     * 设置有效期 单位秒
     *
     * @author rcl
     * @date 2018/7/17 21:27
     *//*

    public static Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{}  error", key, e);
            RedisPool.returnBrokenResouce(jedis);
            return result;
        }
        RedisPool.returnResouce(jedis);
        return result;

    }

    */
/**
     * @param exTime 过期时间 单位秒
     * @author rcl
     * @date 2018/7/17 21:26
     *//*

    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("Setex key:{} value:{} error", key, value, e);
            RedisPool.returnBrokenResouce(jedis);
            return result;
        }
        RedisPool.returnResouce(jedis);
        return result;

    }

    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("Set key:{} value:{} error", key, value, e);
            RedisPool.returnBrokenResouce(jedis);
            return result;
        }
        RedisPool.returnResouce(jedis);
        return result;

    }

    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisPool.returnBrokenResouce(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;

    }

    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisPool.returnBrokenResouce(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;

    }



}
*/
