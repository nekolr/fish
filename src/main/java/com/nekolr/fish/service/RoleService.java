package com.nekolr.fish.service;

import com.nekolr.fish.entity.Role;
import com.nekolr.fish.service.dto.RoleDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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


    /**
     * 根据 ID 查询
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'id:' + #p0")
    RoleDTO findById(Long id);

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @CacheEvict(allEntries = true)
    RoleDTO saveRole(Role role);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @CacheEvict(allEntries = true)
    RoleDTO updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @CacheEvict(allEntries = true)
    void deleteRole(Long id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @CacheEvict(allEntries = true)
    void deleteBatch(List<Long> ids);
}
