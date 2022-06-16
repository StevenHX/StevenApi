package com.steven.demo01.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param key
     * @param value
     * @return
     * @Title: set
     * @Description: 写入缓存
     * @author mario
     * @return: boolean
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key
     * @param value
     * @return
     * @Title: increment
     * @Description: 计数
     * @author mario
     * @return: boolean
     */
    public long increment(final String key, Long value) {
        long result = 0;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.increment(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key
     * @param value
     * @param expireTime
     * @return
     * @Title: set
     * @Description: 写入缓存设置时效时间
     * @author mario
     * @return: boolean
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param key
     * @param value
     * @param expireTime
     * @return
     * @Title: set
     * @Description: 写入缓存设置时效时间
     * @author mario
     * @return: boolean
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit t) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, t);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param keys
     * @Title: remove
     * @Description: 批量删除对应的value
     * @author mario
     * @return: void
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * @param pattern
     * @Title: removePattern
     * @Description: 批量删除key
     * @author mario
     * @return: void
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * @param key
     * @Title: remove
     * @Description: 删除对应的value
     * @author mario
     * @return: void
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * @param key
     * @return
     * @Title: exists
     * @Description: 判断缓存中是否有对应的value
     * @author mario
     * @return: boolean
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @param key
     * @return
     * @Title: get
     * @Description: 读取
     * @author mario
     * @return: Object
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * @param key
     * @param expireTime
     * @Title: SetKeyExpireTime
     * @Description: 设置key的过期时间
     * @author mario
     * @return: void
     */
    public void SetKeyExpireTime(String key, Long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * @param key
     * @param hashKey
     * @param value
     * @Title: hmSet
     * @Description: 哈希添加数据
     * @author mario
     * @return: void
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * @param key
     * @return
     * @Title: hmSize
     * @Description: 哈希获取key数据大小
     * @author mario
     * @return: Long
     */
    public Long hmSize(String key) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.size(key);
    }

    /**
     * @param key
     * @param hashKey
     * @return
     * @Title: hmGet
     * @Description: 哈希获取数据
     * @author mario
     * @return: Object
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * @param key
     * @param hashKeys
     * @return
     * @Title: hmGet
     * @Description: 通过key+哈希获取集合
     * @author mario
     * @return: List
     */
    public List hmGet(String key, Collection<Object> hashKeys) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.multiGet(key, hashKeys);
    }

    /**
     * @param key
     * @return
     * @Title: hmGetHshKeys
     * @Description: 获取hashKey的集合
     * @author mario
     * @return: Set<Object>
     */
    public Set<Object> hmGetHshKeys(String key) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.keys(key);
    }

    /**
     * @param key
     * @param hashKeys
     * @return
     * @Title: hmDelete
     * @Description: 哈希删除数据
     * @author mario
     * @return: Object
     */
    public Object hmDelete(String key, Object... hashKeys) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.delete(key, hashKeys);
    }

    /**
     * @param k
     * @param v
     * @Title: lPush
     * @Description: 列表添加
     * @author mario
     * @return: void
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * @param key
     * @param values
     * @Title: lPushAll
     * @Description: 列表添加集合
     * @author mario
     * @return: void
     */
    public void lPushAll(String key, List<? extends Object> values) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPushAll(key, values);
    }

    /**
     * @param k
     * @param l
     * @param l1
     * @return
     * @Title: lRange
     * @Description: 列表获取
     * @author mario
     * @return: List<? extends Object>
     */
    public List<? extends Object> lRange(String k, long l, long l1) {
        ListOperations<String, List<? extends Object>> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * @param key
     * @param value
     * @Title: add
     * @Description: 集合添加
     * @author mario
     * @return: void
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * @param key
     * @return
     * @Title: setMembers
     * @Description: 集合获取
     * @author mario
     * @return: Set<Object>
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }


    /**
     * @param key
     * @param value
     * @param scoure
     * @Title: zAdd
     * @Description: 有序集合添加
     * @author mario
     * @return: void
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * @param key
     * @param setValue
     * @param scoure
     * @Title: zAddAll
     * @Description: 一次性添加set集合
     * @author mario
     * @return: void
     */
    public void zAddAll(String key, Set<Object> setValue, double scoure) {
        for (Object object : setValue) {
            zAdd(key, object, scoure);
        }
    }

    /**
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     * @Title: rangeByScore
     * @Description: 有序集合获取
     * @author mario
     * @return: Set<Object>
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    /**
     * @param key
     * @param minScoure
     * @param maxSource
     * @return
     * @Title: removeRangeByScore
     * @Description: 根据分数范围删除ZSet集合中的元素（minScoure和maxSource相同，则是根据分数精确删除）
     * @author mario
     * @return: long
     */
    public long removeRangeByScore(String key, double minScoure, double maxSource) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.removeRangeByScore(key, minScoure, maxSource);
    }

    public void zRemove(String key, Object... values) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.remove(key, values);
    }


    /**
     * 获取 过期时间
     *
     * @param key
     * @param timeUnit
     */
    public Long getKeyExpireTime(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }
}