package com.mmall.util;

import com.mmall.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

@Slf4j
public class RedisShardedPoolUtil {
    /**
     * 设置有效期 单位秒
     *
     * @author rcl
     * @date 2018/7/17 21:27
     */
    public static Long expire(String key, int exTime) {
        ShardedJedis jedis = null;
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

    /**
     * @param exTime 过期时间 单位秒
     * @author rcl
     * @date 2018/7/17 21:26
     */
    public static String setEx(String key, String value, int exTime) {
        ShardedJedis jedis = null;
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
        ShardedJedis jedis = null;
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
        ShardedJedis jedis = null;
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
        ShardedJedis jedis = null;
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

    public static Long setnx(String key, String value) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("setnx key:{} value:{} error", key, value, e);
            RedisPool.returnBrokenResouce(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;
    }

    public static String getset(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            log.error("getset key:{} value:{} error", key, value, e);
            RedisPool.returnBrokenResouce(jedis);
            return null;
        }
        RedisPool.returnResouce(jedis);
        return result;
    }

}
