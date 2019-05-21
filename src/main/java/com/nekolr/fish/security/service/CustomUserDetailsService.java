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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * 自定义 UserDetailsService
 *
 * @author nekolr
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
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
     *
     * @param user
     * @return
     */
    private Collection<GrantedAuthority> mapToGrantedAuthorities(User user) {
        return authorityService.mapToGrantedAuthorities(user);
    }
}
