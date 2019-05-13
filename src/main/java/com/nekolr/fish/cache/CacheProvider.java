package com.nekolr.fish.cache;

/**
 * @author nekolr
 */
public interface CacheProvider {

    /**
     * 根据 key 获取缓存
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 创建缓存
     *
     * @param key
     * @param value
     * @return
     */
    void save(String key, Object value);

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    boolean delete(String key);

    /**
     * 清空缓存
     */
    void clear();
}
