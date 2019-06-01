package com.nekolr.fish.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
public class DepartmentDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Long pid;

    private Timestamp createTime;

    private Boolean enabled;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DepartmentDTO> children;

    private Long sort;

    /**
     * 可以用来通过国际化输出
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
