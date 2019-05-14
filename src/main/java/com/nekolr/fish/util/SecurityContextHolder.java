package com.nekolr.fish.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityContextHolder {

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new RuntimeException(HttpStatus.UNAUTHORIZED.toString());
        }

        return userDetails;
    }
}
