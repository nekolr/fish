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
@Table(name = "permission")
public class Permission implements Serializable {

    /**
     * 权限 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    /**
     * 权限名称
     */
    @NotBlank
    private String name;

    /**
     * 上级权限
     */
    @NotNull
    @Column(name = "pid", nullable = false)
    private Long pid;

    /**
     * 描述
     */
    private String description;

    /**
     * 角色集合
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 排序字段，数字越大越靠后
     */
    @NotNull
    private Long sort;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
