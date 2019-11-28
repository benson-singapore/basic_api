package com.spring.bacisic.admin.common.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * redis service
 *
 * @author zhangby
 * @date 2019-05-15 09:34
 */
@Service
public class IRedisService {
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * redis service
     */
    @Autowired
    protected StringRedisTemplate redisTemplate;

    /**
     * Write to redis cache
     *
     * @param key key
     * @param value value
     * @return boolean
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        if (value != null) {
            try {
                ValueOperations operations = redisTemplate.opsForValue();
                if (value instanceof String) {
                    operations.set(key, value.toString());
                } else {
                    operations.set(key, JSON.toJSONString(value));
                }
                result = true;
            } catch (Exception e) {
                logger.info("Writing redis cache failed! The error message is:" + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Write redis cache (set expire survival time)
     *
     * @param key key
     * @param value value
     * @param expire time
     * @return boolean
     */
    public boolean set(final String key, Object value, Long expire) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            if (value instanceof String) {
                operations.set(key, value.toString());
            } else {
                operations.set(key, JSON.toJSONString(value));
            }
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.info("Writing to the redis cache (setting the expire lifetime) failed! The error message is:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Read redis cache
     *
     * @param key key
     * @return object
     */
    public Object get(final String key) {
        Object result = null;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            result = operations.get(key);
        } catch (Exception e) {
            logger.info("Failed to read redis cache! The error message is:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Read redis to entity
     *
     * @param key   redis key
     * @param clazz 实体类class
     * @param <T>   泛型
     * @return T
     */
    public <T> T getBean(final String key, Class<T> clazz) {
        return Optional.ofNullable(get(key))
                .map(o -> JSON.parseObject(o.toString(), clazz))
                .orElse(null);
    }

    /**
     * Read redis to List
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> getArrayBean(final String key, Class<T> clazz) {
        return Optional.ofNullable(get(key))
                .map(o -> JSON.parseArray(o.toString(), clazz))
                .orElse(null);
    }


    /**
     * Determine if there is a corresponding key in the redis cache
     *
     * @param key key
     * @return boolean
     */
    public boolean exists(final String key) {
        boolean result = false;
        try {
            result = redisTemplate.hasKey(key);
        } catch (Exception e) {
           logger.info("Determine if there is a corresponding key in the redis cache failed! The error message is:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Redis deletes the corresponding value according to the key
     *
     * @param key key
     * @return boolean
     */
    public boolean remove(final String key) {
        boolean result = false;
        try {
            if (exists(key)) {
                redisTemplate.delete(key);
            }
            result = true;
        } catch (Exception e) {
            logger.info("Redis fails to delete the corresponding value according to the key! The error message is:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Redis deletes the corresponding value according to the keywords batch
     *
     * @param keys keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
}