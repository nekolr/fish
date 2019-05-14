package com.nekolr.fish.service;

import com.nekolr.fish.entity.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.scheduling.annotation.Async;

/**
 * @author nekolr
 */
@CacheConfig(cacheNames = "log")
public interface LogService {

    /**
     * 保存日志
     *
     * @param log
     */
    @Async
    void saveLog(Log log);
}
