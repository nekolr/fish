package com.nekolr.fish.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author nekolr
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class Department implements Serializable {

    /**
     * 部门 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 部门名称
     */
    @NotBlank
    private String name;

    /**
     * 职务描述
     */
    private String description;

    /**
     * 上级部门
     */
    @NotNull
    private Long pid;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 是否启用
     */
    @NotNull
    private Boolean enabled;

    /**
     * 排序字段，数字越大越靠后
     */
    @NotNull
    private Long sort;

}
