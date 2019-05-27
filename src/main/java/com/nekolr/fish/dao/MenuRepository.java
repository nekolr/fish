package com.nekolr.fish.dao;

import com.nekolr.fish.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor {

    /**
     * 根据角色 ID 获取菜单列表
     *
     * @param id
     * @return
     */
    List<Menu> findAllByRoles_Id(Long id);
}
