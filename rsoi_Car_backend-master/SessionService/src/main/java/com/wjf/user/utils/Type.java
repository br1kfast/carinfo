package com.wjf.user.utils;


/**
 * @author JunFei
 * @data
 * @purpose
 */
public enum Type {
    /**
     * 成功
     */
    SUCCESS(0),
    /**
     * 失败
     */
    FAIL(1),
    /**
     * 警告
     */
    WARN(301),
    /**
     * 错误
     */
    ERROR(500);
    private final int value;

    Type(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
