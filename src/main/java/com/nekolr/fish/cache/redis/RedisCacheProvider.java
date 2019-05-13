package com.nekolr.fish.cache.redis;

import com.nekolr.fish.cache.CacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author nekolr
 */
@Service
public class RedisCacheProvider implements CacheProvider {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }


    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public void clear() {
        redisTemplate.execute(connection -> {
            connection.flushDb();
            return "ok";
        }, false);
    }
}
