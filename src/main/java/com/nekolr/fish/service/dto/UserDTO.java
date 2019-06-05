package com.nekolr.fish.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    @NotNull
    private Long id;

    @NotBlank
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    private String realName;

    private String avatar;

    private String email;

    private Integer gender;

    private Integer age;

    private String phone;

    private Boolean enabled;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    @ApiModelProperty(hidden = true)
    private JobDTO job;

    @ApiModelProperty(hidden = true)
    private DepartmentDTO department;

    @ApiModelProperty(hidden = true)
    private Set<RoleDTO> roles;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", realName='" + realName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                ", createTime=" + createTime +
                ", lastPasswordResetTime=" + lastPasswordResetTime +
                '}';
    }
}
