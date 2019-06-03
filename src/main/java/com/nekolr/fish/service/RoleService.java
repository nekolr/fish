package com.nekolr.fish.service;

import com.nekolr.fish.entity.Role;
import com.nekolr.fish.service.dto.RoleDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "role")
public interface RoleService {

    /**
     * 根据用户账号查询用户角色信息
     *
     * @param username
     * @return
     */
    @Cacheable(key = "'loadRoleSetByUserId:' + #p0")
    List<Role> findByUsername(String username);


    /**
     * 获取所有的角色列表
     *
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<RoleDTO> findAll();
}
