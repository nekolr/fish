package com.nekolr.fish.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author nekolr
 */
@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Timestamp createTime;

    private Long sort;

    private Set<ResourceDTO> resources;

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
