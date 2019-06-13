package com.nekolr.fish.dao;

import com.nekolr.fish.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor {

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    Permission findByName(String name);
}
