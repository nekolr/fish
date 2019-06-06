package com.nekolr.fish.service;

import com.nekolr.fish.service.dto.CommonDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "permission")
public interface PermissionService {

    /**
     * 获取所有权限列表
     *
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<CommonDTO> findAll();
}
