package com.nekolr.fish.service;

import com.nekolr.fish.service.dto.CommonDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "department")
public interface DepartmentService {

    /**
     * 获取所有部门列表
     *
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<CommonDTO> findAll();
}
