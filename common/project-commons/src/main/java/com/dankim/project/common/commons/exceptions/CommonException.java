package com.dankim.project.common.commons.exceptions;

public abstract class CommonException extends RuntimeException {
    private final String message;

    public CommonException(String message) {
        this.message = message;
    }
}
