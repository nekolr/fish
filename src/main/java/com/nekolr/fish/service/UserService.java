package com.nekolr.fish.service;

import com.nekolr.fish.entity.User;
import com.nekolr.fish.service.dto.UserDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
    @Cacheable(key = "'username:' + #p0")
    User findByUsername(String username);

    /**
     * 根据 ID 查询
     *
     * @param id
     * @return
     */
    @Cacheable(key = "'id:' + #p0")
    User findById(Long id);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @CacheEvict(allEntries = true)
    UserDTO saveUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @CacheEvict(allEntries = true)
    UserDTO updateUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    @CacheEvict(allEntries = true)
    void deleteUser(Long id);

}
