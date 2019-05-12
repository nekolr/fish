package com.nekolr.fish.service;

import com.nekolr.fish.entity.User;

/**
 * @author nekolr
 */
public interface UserService {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

}
