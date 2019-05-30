package com.nekolr.fish.exception;

/**
 * 实体已经存在异常
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(String message) {
        super(message);
    }
}
