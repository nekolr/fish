package com.nekolr.fish.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 接收需要校验凭证的用户信息
 */
@Getter
@Setter
@ToString
public class AuthenticationUser implements Serializable {

    private String username;

    private String password;
}
