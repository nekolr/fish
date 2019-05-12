package com.nekolr.fish.dao;

import com.nekolr.fish.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
    /**
     * 根据账号查询用户信息
     *
     * @param username
     * @return
     */
    User findByUsername(String username);
}
