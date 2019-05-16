package com.nekolr.fish.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * 用户 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @NotBlank
    @Column(unique = true)
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 随机盐（用于加密密码）
     */
    @NotBlank
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 部门
     */
    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    /**
     * 职务
     */
    @OneToOne
    @JoinColumn(name = "job_id")
    private Job job;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 头像
     */
    private String avatar;

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
     * 上次重置密码时间（用于计算密码是否过期）
     */
    @Column(name = "last_password_reset_time")
    private Date lastPasswordResetTime;

    /**
     * 角色集合
     */
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;
}
