package com.nekolr.fish.security.service;

import com.nekolr.fish.dao.RoleRepository;
import com.nekolr.fish.entity.Role;
import com.nekolr.fish.entity.User;
import com.nekolr.fish.security.CustomGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限服务
 *
 * @author nekolr
 */
@Service
@CacheConfig(cacheNames = "role")
public class AuthorityService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 根据用户 ID 查询权限
     *
     * @param user
     * @return
     */
    @Cacheable(key = "'loadAuthoritiesByUsername:' + #p0.username")
    public Collection<GrantedAuthority> mapToGrantedAuthorities(User user) {
        Set<Role> roles = roleRepository.findByUsers_Id(user.getId());

        return roles.stream().flatMap(role -> role.getResources().stream())
                .map(resource -> new CustomGrantedAuthority(resource.getName()))
                .collect(Collectors.toList());
    }
}
