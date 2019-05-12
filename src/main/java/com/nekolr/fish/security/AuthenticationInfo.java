package com.nekolr.fish.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

/**
 * 签发令牌后返回给前端的实体
 */
@Getter
@AllArgsConstructor
public class AuthenticationInfo implements Serializable {

    private String token;

    private UserDetails user;
}
