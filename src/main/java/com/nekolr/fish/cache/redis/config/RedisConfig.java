package com.nekolr.fish.cache.redis.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Redis 配置类
 *
 * @author nekolr
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    /**
     * 使用配置文件
     */
    @Autowired
    private CacheProperties cacheProperties;

    /**
     * 自定义缓存 key 生成策略
     * <p>
     * 使用方法 @Cacheable(keyGenerator="keyGenerator")
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(JSON.toJSONString(obj).hashCode());
            }
            return sb.toString();
        };
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration());

        List<String> cacheNames = this.cacheProperties.getCacheNames();
        if (!cacheNames.isEmpty()) {
            builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
        }
        return builder.build();
    }

    /**
     * 缓存配置
     *
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        // 生成一个默认的缓存配置
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        // 缓存过期时间
        configuration = configuration.entryTtl(cacheProperties.getRedis().getTimeToLive())
                // 设置 key 值序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer()))
                // 设置 value 值序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()));
        // 是否允许缓存空值
        if (!cacheProperties.getRedis().isCacheNullValues()) {
            configuration = configuration.disableCachingNullValues();
        }
        // 如果配置中存在前缀则添加
        if (StringUtils.isNotBlank(cacheProperties.getRedis().getKeyPrefix())) {
            configuration = configuration.prefixKeysWith(cacheProperties.getRedis().getKeyPrefix());
        }
        // 是否使用前缀
        if (!cacheProperties.getRedis().isUseKeyPrefix()) {
            configuration = configuration.disableKeyPrefix();
        }

        return configuration;
    }

    /**
     * RedisTemplate
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        // 使用 StringRedisSerializer 序列化 redis 的 key
        redisTemplate.setKeySerializer(stringRedisSerializer());
        // 使用 Jackson2JsonRedisSerializer 序列化 redis 的 value
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(stringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }


    /**
     * Redis Value 序列化
     *
     * @return
     */
    @Bean(name = "jackson2JsonRedisSerializer")
    public RedisSerializer<Object> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        return serializer;
    }

    /**
     * Redis Key 序列化
     *
     * @return
     */
    @Bean(name = "stringRedisSerializer")
    public RedisSerializer<String> stringRedisSerializer() {
        return new StringRedisSerializer();
    }
}