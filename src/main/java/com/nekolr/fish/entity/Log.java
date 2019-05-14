package com.nekolr.fish.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "Log")
public class Log implements Serializable {

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }

    /**
     * 日志 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户
     */
    private String username;


    /**
     * 描述
     */
    private String description;

    /**
     * 方法
     */
    private String method;

    /**
     * 方法参数
     */
    @Column(columnDefinition = "text")
    private String params;

    /**
     * 日志类型
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 错误信息
     */
    @Column(name = "error_message", columnDefinition = "text")
    private String errorMessage;

    /**
     * IP 地址
     */
    private String ip;

    /**
     * 请求耗时
     */
    private Long time;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;
}
