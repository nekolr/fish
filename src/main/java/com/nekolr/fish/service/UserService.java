package com.nekolr.fish.service;

import com.nekolr.fish.entity.User;
import com.nekolr.fish.service.dto.UserDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author nekolr
 */
@CacheConfig(cacheNames = "user")
public interface UserService {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    @Cacheable(key = "#p0")
    User findByUsername(String username);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @CachePut(key = "#user.username")
    UserDTO saveUser(User user);

}
