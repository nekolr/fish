package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class DepartmentDTO extends CommonDTO {

    private String name;

    private String description;

    private Timestamp createTime;

    private Boolean enabled;

    private Long sort;

    /**
     * 可以用来通过国际化输出
     *
     * @return
     */
    public String getLabel() {
        return name;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pid=" + pid +
                ", createTime=" + createTime +
                ", enabled=" + enabled +
                ", sort=" + sort +
                '}';
    }
}
