package com.nekolr.fish.service;

import com.nekolr.fish.service.dto.JobDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "job")
public interface JobService {

    /**
     * 获取所有部门列表
     *
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<JobDTO> findAll();
}
