package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PermissionDTO extends CommonDTO {

    private String name;

    private String description;

    private Timestamp createTime;

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
