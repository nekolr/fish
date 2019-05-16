package com.nekolr.fish.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nekolr.fish.constant.ResourceType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "resource")
public class Resource implements Serializable {

    /**
     * 资源 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 资源名称
     */
    @NotBlank
    private String name;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 资源类型
     */
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    /**
     * 上级 ID
     */
    @NotNull
    private Long pid;

    /**
     * 排序字段，数字越大越靠后
     */
    @NotNull
    private Long sort;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;


    /**
     * 角色集合
     */
    @ManyToMany(mappedBy = "resources")
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", pid=" + pid +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}
