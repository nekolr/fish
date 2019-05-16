package com.nekolr.fish.security.service;

import com.nekolr.fish.entity.User;
import com.nekolr.fish.security.CustomUserDetails;
import com.nekolr.fish.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 自定义 UserDetailsService
 *
 * @author nekolr
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        // 用户不存在
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("Username [ " + username + " ] not found");
        }

        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getSalt(),
                user.getRealName(),
                user.getAvatar(),
                user.getCreateTime(),
                user.getEnabled(),
                user.getLastPasswordResetTime(),
                this.mapToGrantedAuthorities(user)
        );
    }

    /**
     * 收集用户资源（权限）集合
     * <p>
     * 这里不直接使用 user.getRoles() 来获取角色集合而是额外查询一次数据库，
     * 这么做的原因是如果用户信息是通过缓存获取的，并且如果此时 roles 是空的，那么
     * 很大可能会触发懒加载机制去查询数据库，而这整个过程 hibernate session 根本就
     * 没有初始化（没有使用它来查询数据库），所以会报错。
     *
     * @param user
     * @return
     */
    private Collection<GrantedAuthority> mapToGrantedAuthorities(User user) {
        return authorityService.mapToGrantedAuthorities(user);
    }
}
