package com.nekolr.fish.service;

import com.nekolr.fish.service.dto.MenuDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "menu")
public interface MenuService {

    /**
     * 根据角色 ID 获取菜单列表
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'loadMenuListByRoleId:' + #p0")
    List<MenuDTO> findAllByRoleId(Long id);

    /**
     * 根据角色 ID 集合查询菜单列表
     *
     * @param ids
     * @return
     */
    @Cacheable(keyGenerator = "keyGenerator")
    List<MenuDTO> findAllByRoleIds(List<Long> ids);
}
