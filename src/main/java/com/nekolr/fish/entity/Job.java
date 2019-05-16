package com.nekolr.fish.entity;

import lombok.Getter;
import lombok.Setter;
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
@Entity
@Table(name = "job")
public class Job implements Serializable {

    /**
     * 职务 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 职务名称
     */
    @NotBlank
    private String name;

    /**
     * 职务描述
     */
    private String description;

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

    /**
     * 职务所属部门
     */
    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;


    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", enabled=" + enabled +
                ", sort=" + sort +
                '}';
    }
}
