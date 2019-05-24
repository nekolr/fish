package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class PermissionDTO implements Serializable {

    private Long id;

    private String name;

    private Long pid;

    private String description;

    private Timestamp createTime;

    private List<PermissionDTO> children;

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
