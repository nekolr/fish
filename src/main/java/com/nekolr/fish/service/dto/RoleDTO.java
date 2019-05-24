package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Timestamp createTime;

    private Long sort;

    private Set<PermissionDTO> permissions;

    private Set<MenuDTO> menus;

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", sort=" + sort +
                '}';
    }
}
