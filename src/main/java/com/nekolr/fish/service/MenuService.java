package com.nekolr.fish.service;

import com.nekolr.fish.entity.Menu;
import com.nekolr.fish.service.dto.CommonDTO;
import com.nekolr.fish.service.dto.MenuDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
    List<CommonDTO> findAllByRoleIds(List<Long> ids);


    /**
     * 查询菜单列表
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
    MenuDTO findById(Long id);

    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    @CacheEvict(allEntries = true)
    MenuDTO saveMenu(Menu menu);

    /**
     * 更新菜单
     *
     * @param menu
     * @return
     */
    @CacheEvict(allEntries = true)
    MenuDTO updateMenu(Menu menu);

    /**
     * 删除菜单
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void deleteMenu(Long id);
}
