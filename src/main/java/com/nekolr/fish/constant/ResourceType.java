package com.nekolr.fish.constant;

/**
 * 资源类型
 */
public enum ResourceType {

    MENU("MENU"),
    PERMISSION("PERMISSION");

    private String value;

    ResourceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
