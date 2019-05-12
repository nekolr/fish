package com.nekolr.fish.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * 自定义实现 GrantedAuthority
 */
public class CustomGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 资源（权限）
     */
    private String resource;

    public CustomGrantedAuthority(String resource) {
        this.resource = resource;
    }

    @Override
    public String getAuthority() {
        return resource;
    }
}
