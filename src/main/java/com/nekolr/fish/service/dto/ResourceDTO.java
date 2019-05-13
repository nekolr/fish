package com.nekolr.fish.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author nekolr
 */
@Data
public class ResourceDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private List<ResourceDTO> children;

    private String type;

    private Long sort;

    private Timestamp createTime;

    @Override
    public String toString() {
        return "ResourceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}
