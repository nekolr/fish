package com.nekolr.fish.service;

import com.nekolr.fish.entity.Permission;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.dto.PermissionDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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


    /**
     * 根据 ID 查询
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'id:' + #p0")
    PermissionDTO findById(Long id);


    /**
     * 保存权限
     *
     * @param permission
     * @return
     */
    @CacheEvict(allEntries = true)
    PermissionDTO savePermission(Permission permission);

    /**
     * 更新权限
     *
     * @param permission
     * @return
     */
    @CacheEvict(allEntries = true)
    PermissionDTO updatePermission(Permission permission);


    /**
     * 删除用户
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void deletePermission(Long id);
}
