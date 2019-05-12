package com.nekolr.fish.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author nekolr
 */
@Data
public class UserDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    private String realName;

    private String avatar;

    private String email;

    private String phone;

    private Boolean enabled;

    private Timestamp createTime;

    private Date lastPasswordResetTime;
}
