package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class MenuDTO extends CommonDTO {

    private String name;

    private Long sort;

    private String path;

    private Boolean outside;

    private String icon;

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
