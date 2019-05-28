package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class MenuDTO implements Serializable {

    private Long id;

    private String name;

    private Long sort;

    private String path;

    private Long pid;

    private Boolean outside;

    private String icon;

    private List<MenuDTO> children;

    private Timestamp createTime;

    @Override
    public String toString() {
        return "MenuDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", path='" + path + '\'' +
                ", pid=" + pid +
                ", outside=" + outside +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
