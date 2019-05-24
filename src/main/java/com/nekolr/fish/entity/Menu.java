package com.nekolr.fish.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "menu")
public class Menu implements Serializable {

    /**
     * 菜单 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank
    private String name;

    /**
     * 排序字段，数字越大越靠后
     */
    @NotNull
    private Long sort;

    /**
     * 菜单路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 对应前端组件
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 上级菜单 ID
     */
    @Column(name = "pid", nullable = false)
    private Long pid;

    /**
     * 是否为外链
     */
    @Column(name = "outside")
    private Boolean outside;

    /**
     * 角色集合
     */
    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<Role> roles;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", icon='" + icon + '\'' +
                ", pid=" + pid +
                ", outside=" + outside +
                ", createTime=" + createTime +
                '}';
    }
}
