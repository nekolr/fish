package com.nekolr.fish.dao;

import com.nekolr.fish.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor {

    /**
     * 根据用户 ID 查询用户角色信息
     *
     * @param id
     * @return
     */
    Set<Role> findByUsers_Id(Long id);


    /**
     * 根据用户账号查询用户角色信息
     *
     * @param username
     * @return
     */
    Set<Role> findByUsers_Username(String username);

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    Role findByName(String name);
}
