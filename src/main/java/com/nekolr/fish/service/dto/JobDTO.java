package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class JobDTO implements Serializable {

    private Long id;

    private Long sort;

    private String name;

    private Boolean enabled;

    private Timestamp createTime;

    private DepartmentDTO department;

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", createTime=" + createTime +
                '}';
    }
}
