package com.nekolr.fish.constant;

/**
 * @author nekolr
 */
public enum LogType {

    INFO("操作日志", "INFO"),
    ERROR("错误日志", "ERROR");

    private String name;
    private String value;

    LogType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
