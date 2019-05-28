package com.nekolr.fish.dao;

import com.nekolr.fish.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor {

    /**
     * 根据角色 ID 获取菜单列表
     *
     * @param id
     * @return
     */
    List<Menu> findAllByRoles_Id(Long id);

    /**
     * 根据角色 ID 集合查询菜单列表
     *
     * @param roleIds
     * @return
     */
    @Query(value = "SELECT DISTINCT m.* FROM menu m LEFT JOIN role_menu rm on m.id = rm.menu_id WHERE role_id IN(:roleIds)", nativeQuery = true)
    List<Menu> findAllByRoleIds(@Param("roleIds") List<Long> roleIds);
}
